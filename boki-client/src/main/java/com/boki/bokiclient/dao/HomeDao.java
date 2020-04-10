package com.boki.bokiclient.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    List<List<?>> findPosts(@Param("type")Integer type,
                            @Param("titleKey")String titleKey,
                            @Param("start") Integer start,
                            @Param("end")Integer end);

}
