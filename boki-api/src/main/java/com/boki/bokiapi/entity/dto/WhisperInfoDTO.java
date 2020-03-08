package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperInfoDTO {
    private Integer id;
    private Long targetUserId;          //对方
    private String targetUserName;      //对方昵称
    private String targetUserPhoto;     //对方头像
    private String lastContent;         //最后一条信息
    private String lastReplyTime;       //会后回复时间
}
