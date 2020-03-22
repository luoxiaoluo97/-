package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotEmpty(message = StatusName.NULL_REASON)
    @Pattern(regexp = ".*[\\S]+.*",message = StatusName.NULL_REASON)    //不能全空格
    private String reason;          //理由
}
