package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户更改个人页面请求
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserInfoDTO {
    @NotEmpty
    private String mail;		//邮箱即是账号

    @NotBlank(message = "ERROR_USERNAME_FORMAT")
    @Size(min = 1,max = 12,message = "ERROR_USERNAME_SIZE")
    private String userName;          //昵称

    @NotEmpty(message = "NULL_SEX")
    private String sex;          //用户性别,保密，男，女

    @Past(message = "ERROR_BIRTH")
    private String birth;          //出生日期

    private String from;          //来自何地
    private String intro;           //自我简介
    private String show;         //用户个性签名
}
