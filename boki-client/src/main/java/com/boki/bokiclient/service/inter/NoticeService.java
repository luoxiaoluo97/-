package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description: 通知
 */
public interface NoticeService {

    /**
     * 发送私信通知
     * @param msg
     * @param elem
     */
    void sendWhisperNotice(NoticeMessage msg, NoticeElem elem);

    /**
     * 给粉丝发送发帖动态
     */
    void sendNoticeToFans(NoticeMessage msg, NoticeElem elem);

    /**
     * 回帖时给楼主通知
     */
    void sendReplyNotice(NoticeMessage msg, NoticeElem elem);

    /**
     * 楼层回复时给层主通知
     */
    void sendStoreyReplyNotice(NoticeMessage msg, NoticeElem elem);

    /**
     * 楼层回复时给被回复者通知
     */
    void sendNoticeWhenGotAReply(NoticeMessage msg, NoticeElem elem);

    /**
     * 通过@用户 通知
     */
    void sendNoticeByAt(NoticeMessage msg, NoticeElem elem,Integer where);

    /**
     * 获取通知数量
     */
    Integer getNoticeCount(Long userId);

    /**
     * 打开通知列表
     */
    DataWithTotal getNoticeList(Long userId,Integer page);

    /**
     * 移除某个通知
     */
    Integer removeNotice(Long userId,Integer id);

    /**
     * 清空通知列表
     */
    Integer clearNotice(Long userId);
}
