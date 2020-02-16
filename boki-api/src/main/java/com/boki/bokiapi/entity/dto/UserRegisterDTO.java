package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户注册与改密请求
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class UserRegisterDTO {

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "请输入正确的邮箱.")
    private String mail;		//邮箱即是账号

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}",message = "密码为8~16位的数字+母字组成")
    private String pwd;          //密码

    @NotEmpty
    @Pattern(regexp = "\\d{8}",message = "校验码格式错误")
    private String checkCode;       //邮箱校验码
}
