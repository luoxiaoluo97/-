package com.boki.bokiapi.entity.dto;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserUpdatePwdDTO {

    private String mail;		//邮箱即是账号

    @NotEmpty(message = StatusName.NULL_PWD)
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}",message = StatusName.ERROR_PWD)
    private String pwd;          //密码

    @NotEmpty(message = StatusName.NULL_CHECK_CODE)
    @Pattern(regexp = "\\d{8}",message = StatusName.ERROR_CHECK_CODE)
    private String checkCode;       //邮箱校验码


}
