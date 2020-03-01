package com.boki.bokiclient.bean;

import com.boki.bokiapi.entity.dto.dbsource.PostTypeDTO;
import com.boki.bokiapi.entity.dto.dbsource.RolePermissionDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserHonorDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserLevelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/3/1
 * @Description:
 */
@Component
public class DBSourceSelectBean {

    @Autowired
    private ArrayList<PostTypeDTO> types;

    @Autowired
    private ArrayList<RolePermissionDTO> rolePermissions;

    @Autowired
    private ArrayList<UserLevelDTO> lvTable;

    @Autowired
    private ArrayList<UserHonorDTO> honors;

    /**
     * 帖子类型查询
     */
    public String getType(Integer typeId){
        String type = null;
        for( PostTypeDTO t : types){
            if(typeId == t.getId()){
                type = t.getType();
                break;
            }
        }
        return type;
    }

    /**
     * 角色查询
     */
    public String getRoleById(Integer roleId){
        String role = null;
        for( RolePermissionDTO rolePermission : rolePermissions){
            if(roleId == rolePermission.getId()){
                role = rolePermission.getRole();
                break;
            }
        }
        return role;
    }

    /**
     * 用户等级表查询
     */
    public Integer getLv(Long exp){
        Integer level = null;
        for( UserLevelDTO lv : lvTable){
            if(exp <= lv.getExp()){
                level = lv.getLevel();
                break;
            }
        }
        return level;
    }

    /**
     * 用户荣誉id查询
     */
    public Integer getHonorId(Integer creditDegree){
        Integer honorId = null;
        for( UserHonorDTO honor : honors){
            if(creditDegree <= honor.getCreditDegree()){
                honorId = honor.getId();
                break;
            }
        }
        return honorId;
    }
}
