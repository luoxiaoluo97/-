package com.boki.bokiapi.value.notice;

import lombok.Getter;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description: 用于不同类型的消息通知
 */
@Getter
public class NoticePrefix {

    /**
     * 通知用户id关键字
     */
    public static final String NOTIFY_BY_UID = "NOTIFY_BY_ID";

    /**
     * 通知用户名关键字
     */
    public static final String NOTIFY_BY_USERNAME = "NOTIFY_BY_USERNAME";



//    /**
//     * 通知具体用户ID
//     */
//    NOTICE_BY_UID("NOTICE_UID_"),
//
//    /**
//     * 通知具体用户userName, @用户名 相关的通知
//     */
//    NOTICE_BY_USERNAME("NOTICE_USERNAME_"),
//
//    /**
//     * 帖子被回复
//     */
//   POST_IS_REPLIED("NOTICE_1"),
//
//    /**
//     * 楼中楼回复
//     */
//    STOREY_IS_REPLIED("NOTICE_2"),
//
//    /**
//     * 被直接@
//     */
//    AT_BY_OTHER("NOTICE_3"),
//
//    /**
//     * 收到私信
//     */
//    WHISPER_NOTICE("NOTICE_4"),
//
//    /**
//     * 删帖通知
//     */
//    POST_REMOVED("NOTICE_5"),
//
//    /**
//     * 删楼、删楼中楼通知
//     */
//    REPLY_REMOVED("NOTICE_6");
//
//    private String prefix;
//
//    NoticePrefix(String prefix){
//        this.prefix = prefix;
//    }

}
