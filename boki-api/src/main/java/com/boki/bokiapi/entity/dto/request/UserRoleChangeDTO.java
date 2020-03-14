package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserRoleChangeDTO {

    private Long id;      //站长id

    @NotNull(message = StatusName.NULL_TARGET_USER)
    private Long userId;        //被改动用户

    @NotNull(message = StatusName.NULL_ROLE)
    @Pattern(regexp = "1|2",message = StatusName.ERROR_ROLE)
    private String roleId;         //将要赋予的职位

}
