package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperDetailVO {
    private Long userId;                //发送方
    private String content;             //私信内容
    private String createTime;          //发送时间

}
