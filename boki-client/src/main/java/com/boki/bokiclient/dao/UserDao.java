package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.dto.UserDTO;
import com.boki.bokiapi.entity.po.FollowPO;
import com.boki.bokiapi.entity.po.PostCollectionPO;
import com.boki.bokiapi.entity.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */
@Mapper
@Repository
public interface UserDao {

    /**
     * 查询用户信息
     */
    UserDTO findUserById(@Param("id")Long id );

    /**
     * 更新用户数据
     */
    int updateUser(UserPO po);

    /**
     * 是否重复收藏
     */
    Integer isPostCollectionExist(PostCollectionPO po);
    /**
     * 添加一条收藏记录
     */
    int insertCollection(PostCollectionPO po);
    /**
     * 移除一条收藏记录，
     */
    int removeCollection(@Param("postId")Long postId,@Param("userId")Long userId);

    /**
     * 用户的帖子收藏列表
     */
    List<List<?>> findPostCollectionByUid(@Param("userId")Long userId,
                                          @Param("start")Integer start,
                                          @Param("end")Integer end);

    /**
     * 是否已关注
     */
    Integer isFollowExist(FollowPO po);

    /**
     * 插入一条关注记录
     */
    Integer insertFollow(FollowPO po);

    /**
     * 移除一条关注
     */
    Integer deleteFollow(FollowPO po);

    /**
     * 关注列表
     */
    List<List<?>> findFollowListByUID(@Param("userId")Long userId,
                                      @Param("start")Integer start,
                                      @Param("end")Integer end);

    /**
     * 粉丝列表
     */
    List<List<?>> findFans(@Param("userId")Long userId,
                           @Param("start")Integer start,
                           @Param("end")Integer end);

    /**
     * 所有粉丝
     */
    List<Long> findAllFans(@Param("userId")Long userId);

    /**
     * 历史发帖
     */
    List<List<?>> findUserLastPosts(@Param("userId")Long userId,
                                    @Param("after")String after,
                                    @Param("start")Integer start,
                                    @Param("end")Integer end);

    /**
     * 历史回复，包括楼层与楼中楼，不包括一楼
     */
    List<List<?>> findReplyHistory(@Param("userId")Long userId,
                                   @Param("start")Integer start,
                                   @Param("end")Integer end);



}
