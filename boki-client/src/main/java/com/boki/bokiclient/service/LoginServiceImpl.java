package com.boki.bokiclient.service;


import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.util.PwdEncryption;
import com.boki.bokiclient.dao.LoginDao;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    /**
     * 登陆验证
     * @param userLoginDTO
     * @return
     */
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
    public int insertUser(UserLoginDTO userLoginDTO) {
        String pwd = PwdEncryption.doubleMd5(userLoginDTO.getPwd());
        userLoginDTO.setPwd(pwd);
        //return loginDao.insertUser(userLoginDTO);
        return 0;
    }
}
