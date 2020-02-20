package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginDao {
    /**
     * 用于登陆
     */
    public UserPO findByMailAndPwd(UserLoginDTO userLoginDTO);

//    public int insertUser(UserLoginDTO userLoginDTO);
}
