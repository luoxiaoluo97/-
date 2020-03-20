package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ReportSendDTO {

    private Integer id;             //目标帖子id|楼层id|楼中楼id

    private Long userId;            //举报者

    private String reason;          //理由
}
