package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.po.UserPO;

public interface LoginService {

    public UserPO findByUserName(String userName);
}
