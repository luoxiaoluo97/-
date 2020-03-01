package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.PostPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostDao {

    /**
     * 增加一条帖子记录
     * @param postPO
     * @return
     */
    int insertPost(PostPO postPO);

    /**
     * 经验增加
     */
    int updateExpById(@Param("id")Long id,@Param("exp") Integer exp);

    /**
     * 回帖+1
     * @param postPO
     * @return
     */
    int insertReply(PostPO postPO);

    /**
     * 删帖
     * @param postPO
     * @return
     */
    int deletePost(PostPO postPO);
}
