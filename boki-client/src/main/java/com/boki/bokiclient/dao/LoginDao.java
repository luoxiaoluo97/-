package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDao {

    public UserPO findByMailAndPwd(UserPO userPO);

    public UserPO findByMailOrUserName(UserPO userPO);

    public int insertUser(UserPO userPO);

    public int updatePwdByMail(UserPO userPO);
}
