package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/6
 * @Description: 回复记录，包括了回帖和楼层回复
 */
@Data
@Accessors(chain = true)
public class ReplyHistoryVO {
    private Long postId;            //所属帖子
    private String title;           //帖子标题
    private Long repliesCount;      //回复数
    private Long floorNo;           //第x楼
    private String content;         //回复内容
    private Integer reportStatus;       //举报状态
    private String reportReason;        //举报理由
}
