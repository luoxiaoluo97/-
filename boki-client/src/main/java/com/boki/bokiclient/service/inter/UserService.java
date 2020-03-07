package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.dto.request.UserInfoDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.UserInfoVO;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */

public interface UserService {

    /**
     * 用户查看自己的信息
     */
    UserInfoVO userInfo(Long userId);

    /**
     * 修改个人信息
     */
    int modifyInfo(UserInfoDTO dto);

    /**
     * 收藏帖子
     */
    int collectPost(Long postId,Long userId);

    /**
     * 取消收藏帖子
     */
    int removeCollection(Long postId,Long userId);

    /**
     * 用户的帖子收藏列表
     */
    DataWithTotal findPostCollectionByUid(Long userId, Integer page);

    /**
     * 关注
     */
    Integer addFollow(Long userId, Long targetUserId);

    /**
     * 取关
     */
    Integer removeFollow(Long userId, Long targetUserId);

    /**
     * 关注列表
     */
    DataWithTotal findFollowList(Long userId, Integer page);

    /**
     * 我的粉丝
     */
    DataWithTotal findFans(Long userId, Integer page);

    /**
     * 我的历史发帖，不包括回复
     */
    DataWithTotal postHistory(Long userId, Integer page);

    /**
     * 我的历史回复，包括楼中楼
     */
    DataWithTotal replyHistory(Long userId, Integer page);
}
