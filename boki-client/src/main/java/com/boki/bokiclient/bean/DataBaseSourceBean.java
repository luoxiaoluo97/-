package com.boki.bokiclient.bean;

import com.boki.bokiapi.entity.dto.dbsource.PostTypeDTO;
import com.boki.bokiapi.entity.dto.dbsource.RolePermissionDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserHonorDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserLevelDTO;
import com.boki.bokiclient.dao.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */

@Component
public class DataBaseSourceBean {

    @Autowired
    private CommonDao commonDao;

    /**
     * 帖子类型表
     */
    @Bean
    public ArrayList<PostTypeDTO> getPostType(){
       return commonDao.getPostType();
    }

    /**
     * 角色表--权限表
     */
    @Bean
    public ArrayList<RolePermissionDTO> getPermissions(){
        return commonDao.getPermissions();
    }

    /**
     * 用户等级表
     */
    @Bean
    public ArrayList<UserLevelDTO> getLevelTable(){
        return commonDao.getLevelTable();
    }

    /**
     * 用户荣誉id表
     */
    @Bean
    public ArrayList<UserHonorDTO> getHonorTable(){
        return commonDao.getHonorTable();
    }

}
