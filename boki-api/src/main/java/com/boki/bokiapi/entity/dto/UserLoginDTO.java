package com.boki.bokiapi.entity.dto;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户登陆请求
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserLoginDTO {

    @NotEmpty(message = StatusName.NULL_MAIL)
    @Email(message = StatusName.ERROR_MAIL)
    private String mail;		//邮箱即是账号

    @NotEmpty(message = StatusName.NULL_PWD)
    @Pattern(regexp = "[a-zA-Z0-9]{8,16}",message = StatusName.ERROR_PWD)
    private String pwd;          //密码

}
