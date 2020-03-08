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
public class WhisperPO {
    private Integer id;
    private Long firstUserId;           //甲
    private Long secondUserId;          //乙
    private String showInFirst;         //在甲方列表显示
    private String showInSecond;        //在乙方列表显示
    private String lastContent;         //最后一条信息

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
