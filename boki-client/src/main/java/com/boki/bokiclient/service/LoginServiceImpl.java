package com.boki.bokiclient.service;


import com.alibaba.fastjson.JSONObject;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.dto.request.UserRegisterDTO;
import com.boki.bokiapi.entity.dto.request.UserUpdatePwdDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.util.MailCheck;
import com.boki.bokiapi.util.PwdEncryption;
import com.boki.bokiclient.dao.LoginDao;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    MailCheck mailCheck;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public UserInfoVO findByMailAndPwd(UserLoginDTO userLoginDTO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userLoginDTO,userPO);
        userPO = loginDao.findByMailAndPwd(userPO);
        if (userPO == null){
            return null;
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userPO,userInfoVO);
        return userInfoVO;
    }

    /**
     * 添加用户，包括验证阶段
     * @param userRegisterDTO
     * @return
     */
    @Override
    public int insertUser(UserRegisterDTO userRegisterDTO) {
        String code = redisTemplate.opsForValue().get(userRegisterDTO.getMail());
        if(code == null){
            throw new BusinessException(userRegisterDTO.getMail()+"验证码不存在或过期。")
                    .setType(RequestResultCode.CHECK_CODE_INVALID);
        }else if(!userRegisterDTO.getCheckCode().equals(code)){
            throw new BusinessException(userRegisterDTO.getMail()+"邮箱验证失败。")
                    .setType(RequestResultCode.CHECK_CODE_VALIDATION_FAILED);
        }
        String pwd = PwdEncryption.doubleMd5(userRegisterDTO.getPwd());
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userRegisterDTO,userPO);
        userPO.setPwd(pwd);
        UserPO result = loginDao.findByMailOrUserName(userPO);
        /*要么名称被占用，要么邮箱被占用*/
        if (result != null) {
            if(userPO.getUserName().equals(result.getUserName())){
                throw new BusinessException(userPO.getUserName()+"名称已被占用").setType(RequestResultCode.USERNAME_ALREADY_EXIST);
            }else {
                throw new BusinessException(userPO.getMail()+"邮箱已被占用").setType(RequestResultCode.MAIL_ALREADY_EXIST);
            }
        }else {
            //注册完成，移除key
            redisTemplate.delete(userRegisterDTO.getMail());
            return loginDao.insertUser(userPO);
        }
    }

    @Override
    public JSONObject sendCheckCodeAndCache(String mail) {
        JSONObject result = new JSONObject();
        result.put("mail",mail);
        if ( redisTemplate.hasKey(mail) ){
            /*返回有效期*/
            result.put("effectiveTime",redisTemplate.getExpire(mail,TimeUnit.SECONDS)+"s");
            return result;
        }
        new Thread(() -> {
            String mailCode = mailCheck.mailSend(mail);
            redisTemplate.opsForValue().set(mail,mailCode);
            redisTemplate.expire(mail, 300, TimeUnit.SECONDS);
        }).start();
        result.put("effectiveTime","300s");
        return result;
    }


    @Override
    public int updatePwdByMail(UserUpdatePwdDTO userUpdatePwdDTO) {
        String code = redisTemplate.opsForValue().get(userUpdatePwdDTO.getMail());
        if(code == null){
            throw new BusinessException(userUpdatePwdDTO.getMail()+"验证码不存在或过期。")
                    .setType(RequestResultCode.CHECK_CODE_INVALID);
        }else if(!userUpdatePwdDTO.getCheckCode().equals(code)){
            throw new BusinessException(userUpdatePwdDTO.getMail()+"邮箱验证失败。")
                    .setType(RequestResultCode.CHECK_CODE_VALIDATION_FAILED);
        }
        String pwd = PwdEncryption.doubleMd5(userUpdatePwdDTO.getPwd());
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userUpdatePwdDTO,userPO);
        userPO.setPwd(pwd);
        int count = loginDao.updatePwdByMail(userPO);
        redisTemplate.delete(userUpdatePwdDTO.getMail());
        return count;
    }


}
