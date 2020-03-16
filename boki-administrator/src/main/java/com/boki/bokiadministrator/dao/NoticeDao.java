package com.boki.bokiadministrator.dao;

import com.boki.bokiapi.entity.po.NoticePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface NoticeDao {
    /**
     * 新增一条通知
     */
    Integer insertNotice(NoticePO po);

    /**
     * 获取帖子id、帖子标题
     */
    Map findPostTitle(Long postId);

    /**
     * 获取帖子id、楼层内容
     */
    Map findReplyContent(Long replyId);

    /**
     * 获取帖子id、楼中楼内容
     */
    Map findStoreyReplyContent(Long storeyReplyId);
}
