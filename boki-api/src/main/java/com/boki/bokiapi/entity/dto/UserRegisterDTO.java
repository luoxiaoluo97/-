package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户注册与改密请求
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserRegisterDTO {

    @NotEmpty(message = "NULL_MAIL")
    @Email(message = "ERROR_MAIL")
    private String mail;		//邮箱即是账号

    @NotEmpty(message = "NULL_PWD")
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}",message = "ERROR_PWD")
    private String pwd;          //密码

    @NotBlank(message = "ERROR_USERNAME_FORMAT")
    @Size(min = 1,max = 12,message = "ERROR_USERNAME_SIZE")
    private String userName;          //昵称

    @NotEmpty(message = "NULL_CHECK_CODE")
    @Pattern(regexp = "\\d{8}",message = "ERROR_CHECK_CODE")
    private String checkCode;       //邮箱校验码
}
