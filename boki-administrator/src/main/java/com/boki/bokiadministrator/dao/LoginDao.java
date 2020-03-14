package com.boki.bokiadministrator.dao;

import com.boki.bokiapi.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDao {

    public UserPO findByMailAndPwd(UserPO userPO);
}
