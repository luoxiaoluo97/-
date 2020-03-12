package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ReportSendDTO {

    @NotNull(message = StatusName.NULL_TARGET_ID)
    private Integer id;             //目标帖子id|楼层id|楼中楼id

    private Long userId;            //举报者

    @NotNull(message = StatusName.NULL_REASON)
    private String reason;          //理由
}
