package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户登陆请求
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserLoginDTO {

    private String mail;		//邮箱即是账号

    private String pwd;          //密码

}
