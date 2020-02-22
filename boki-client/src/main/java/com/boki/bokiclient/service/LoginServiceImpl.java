package com.boki.bokiclient.service;


import com.alibaba.fastjson.JSONObject;
import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.dto.UserRegisterDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.util.MailCheck;
import com.boki.bokiapi.util.PwdEncryption;
import com.boki.bokiclient.dao.LoginDao;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    MailCheck mailCheck;

    @Autowired
    private StringRedisTemplate redisTemplate;


    public UserInfoVO findByMailAndPwd(UserLoginDTO userLoginDTO) {
        UserPO userPO = loginDao.findByMailAndPwd(userLoginDTO);
        if (userPO == null){
            return null;
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userPO,userInfoVO);
        return userInfoVO;
    }

    @Override
    public int insertUser(UserRegisterDTO userRegisterDTO) {
        String pwd = PwdEncryption.doubleMd5(userRegisterDTO.getPwd());
        userRegisterDTO.setPwd(pwd);
        //return loginDao.insertUser(userLoginDTO);
        return 0;
    }

    @Override
    public JSONObject sendCheckCodeAndCache(String mail) throws BusinessException {
        JSONObject result = new JSONObject();
        result.put("mail",mail);
        if ( redisTemplate.hasKey(mail) ){
            result.put("验证码有效时间",redisTemplate.getExpire(mail,TimeUnit.SECONDS)+"s");
            return result;
        }
        String mailCode = mailCheck.mailSend(mail);
        redisTemplate.opsForValue().set(mail,mailCode);
        redisTemplate.expire(mail, 300, TimeUnit.SECONDS);
        result.put("验证码有效时间","300s");
        return result;
    }
}
