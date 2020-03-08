package com.boki.bokiapi.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperDetailPO {
    private Integer id;
    private Integer whisperId;          //所属对话
    private Long userId;                //私信方
    private Long targetUserId;          //接收方
    private String content;             //私信内容

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
