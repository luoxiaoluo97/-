package com.boki.bokiapi.value.notice;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/10
 * @Description: 创建通知时的要素
 */
@Data
@Accessors(chain = true)
public class NoticeElem {
    /**
     * 通知元素,均为可能用到的值，按需取值
     */
    private Long id;             //id，保留字段
    private Long fromUserId;      //来源用户id
    private String fromUserName;    //来源用户名
    private String content;         //内容
    private String shortContent;    //短内容
    private String title;           //帖子标题
    private String reply;           //楼层回复
    private String storeyReply;     //楼中楼回复
    private String reason;          //理由
    private String floorNo;         //第几楼
    private Long storeyId;          //楼层id
    /**
     * 被通知用户信息
     */
    private Long targetUserId;      //被通知用户id
    private String targetUserName;  //被通知用户名
    private Long postId;            //帖子id
    private Integer whisperId;      //私信id
}
