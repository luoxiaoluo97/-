package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.dto.dbsource.PostTypeDTO;
import com.boki.bokiapi.entity.dto.dbsource.RolePermissionDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserHonorDTO;
import com.boki.bokiapi.entity.dto.dbsource.UserLevelDTO;
import com.boki.bokiapi.entity.dto.postdetail.PostDetailDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Repository
@Mapper
public interface CommonDao {
    /**
     * 帖子详情
     */
    public PostDetailDTO getPostDetail(@Param("postId") Long postId);

    /**
     * 楼层列表
     */
    public ArrayList<ReplyDTO> getReplyList(@Param("postId") Long postId);

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
