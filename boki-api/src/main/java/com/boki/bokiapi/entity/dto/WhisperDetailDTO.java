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
public class WhisperDetailDTO {
    private Long userId;
    private String content;             //私信内容
    private String createTime;          //发送时间

}
