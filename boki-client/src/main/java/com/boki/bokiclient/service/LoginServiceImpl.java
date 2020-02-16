package com.boki.bokiclient.service;


import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiclient.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    private LoginDao loginDao;

    public UserPO findByUserName(String userName) {
        return loginDao.findByUserName(userName);
    }
}
