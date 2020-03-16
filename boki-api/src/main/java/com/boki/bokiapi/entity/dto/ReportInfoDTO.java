package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/15
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ReportInfoDTO {
    private Long id;               //帖子或回复id
    private String type;           //帖子post、回复reply、楼层回复storeyReply
    private Long userId;           //所属用户id
    private String userName;       //所属用户
    private String content;        //内容
    private String reportReason;   //理由
    private String reportTime;     //举报时间
}
