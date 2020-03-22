package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class BanUserDTO {

    private Long modifier;                //操作者id

    @NotNull(message = StatusName.NULL_TARGET_USER)
    private Long userId;            //禁封角色

    @NotNull(message = StatusName.NULL_BAN_TIME)
    @Min(value = 1,message = StatusName.ERROR_RANGE)
    private Integer days;           //禁封天数

    private String banUntil;        //解封日期
}
