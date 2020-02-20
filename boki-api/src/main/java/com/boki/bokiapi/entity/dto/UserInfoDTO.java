package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@ToString
public class UserInfoDTO {
    @NotEmpty
    private String mail;		//邮箱即是账号

    @NotEmpty(message = "昵称不能为空")
    @Size(min = 1,max = 12,message = "昵称1~12字")
    private String userName;          //用户名

    @NotEmpty(message = "性别不能为空")
    private String sex;          //用户性别,0保密，1男，2女

    @Past(message = "生日必须是过去的时间")
    private String birth;          //出生日期
    private String from;          //来自何地
    private String intro;           //自我简介
    private String show;         //用户个性签名
}
