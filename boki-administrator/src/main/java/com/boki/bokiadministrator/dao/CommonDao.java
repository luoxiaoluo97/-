package com.boki.bokiadministrator.dao;

import com.boki.bokiapi.entity.dto.dbsource.PostTypeDTO;
import com.boki.bokiapi.entity.dto.dbsource.RolePermissionDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserHonorDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserLevelDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/3/13
 * @Description:
 */
@Mapper
@Repository
public interface CommonDao {
    /**
     * 获取帖子类型
     */
    public ArrayList<PostTypeDTO> getPostType();

    /**
     * 获取角色关联的权限表
     */
    public ArrayList<RolePermissionDTO> getPermissions();

    /**
     * 角色等级表
     */
    public ArrayList<UserLevelDTO> getLevelTable();

    /**
     * 用户荣誉id表
     */
    public ArrayList<UserHonorDTO> getHonorTable();
}
