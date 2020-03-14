package com.boki.bokiadministrator.service;


import com.boki.bokiadministrator.dao.LoginDao;
import com.boki.bokiadministrator.service.inter.LoginService;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

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



}
