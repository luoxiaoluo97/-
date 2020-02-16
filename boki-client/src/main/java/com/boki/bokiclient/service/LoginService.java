package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.po.User;

public interface LoginService {

    public User findByUserName(String userName);
}
