package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description:
 */
@Repository
@Mapper
public interface HomeDao {
    /**
     * 获取帖子列表
     * @return
     */
    public ArrayList<PostDTO> findPosts(@Param("start") Integer start, @Param("end")Integer end);

    /**
     * 帖子总数
     * @return
     */
    public Long getPostsCount();

}
