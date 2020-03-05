package com.boki.bokiclient.dao;

import com.boki.bokiapi.entity.po.PostPO;
import com.boki.bokiapi.entity.po.ReplyPO;
import com.boki.bokiapi.entity.po.StoreyReplyPO;
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
     * 发帖时更新1楼
     */
    int insertFirstReply(PostPO postPO);


    /**
     * 发帖经验增加
     */
    int updateExpById(@Param("id")Long id,@Param("exp") Integer exp);


    /**
     * 删帖
     * @param postPO
     * @return
     */
    int deletePost(PostPO postPO);

    /**
     * 回帖+1
     * @param replyPO
     * @return
     */
    int insertReply(ReplyPO replyPO);

    /**
     * 是否楼主操作
     */
    int findPostByIdAndUser(@Param("postId") Long postId,@Param("userId") Long userId);

    /**
     * 他人回帖，帖子信息更新，经验结算
     */
    int updateReplierSendReply(ReplyPO replyPO);



    /**
     * 楼主顶贴，帖子信息更新，经验结算
     */
    int updatePosterSendReply(ReplyPO replyPO);

    /**
     * 楼中楼回复
     */
    int insertStoreyReply(StoreyReplyPO po);

    /**
     * 是否1楼
     */
    int isFirstFloor(@Param("storeyId")Long storeyId);
    /**
     * 是否层主操作
     */
    int findReplyByIdAndUser(@Param("storeyId") Long postId, @Param("userId") Long userId);

    /**
     * 层主回复自己，经验结算(Settlement)
     */
    int expSettlementByReplier(StoreyReplyPO po);

    /**
     * 他人回复层主,经验结算
     */
    int expSettlementByOther(StoreyReplyPO po);

    /**
     * 删楼
     */
    int deleteReply(ReplyPO replyPO);

    /**
     * 是否用户删除楼中楼
     */
    int isDeleteStoreyReplyBySelf(@Param("storeyReplyId") Long storeyReplyId, @Param("userId") Long userId);
    /**
     * 删楼中楼
     */
    int deleteStoreyReply(StoreyReplyPO po);
}
