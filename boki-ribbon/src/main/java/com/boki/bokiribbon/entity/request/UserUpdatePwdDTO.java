package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    private String pwd;          //密码

    private String checkCode;       //邮箱校验码


}
