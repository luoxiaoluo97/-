package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class PostHistoryAdminVO {
    private Long id;                    //帖子id
    private String title;               //标题
    private String content;             //内容
    private Long repliesCount;          //回复数
    private String type;                //帖子类型，精品贴或其他
    private Integer reportStatus;       //举报状态
    private String reportReason;        //举报理由

    private String createTime;          //发帖时间
}
