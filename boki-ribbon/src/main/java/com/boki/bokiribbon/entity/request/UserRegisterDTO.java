package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户注册与改密请求
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserRegisterDTO {

    private String mail;		//邮箱即是账号

    private String pwd;          //密码

    private String userName;          //昵称

    private String checkCode;       //邮箱校验码
}
