package com.boki.bokiapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String UserID;            //用户ID
    private String UserName;          //用户名
    private String UserPwd;          //密码
    private String UserPassQ;          //密码提示问题
    private String UserPassA;          //密码提示问题答案
    private String UserSex;          //用户性别
    private String UserBirth;          //出生日期
    private String UserBlog;          //用户主页
    private String UserPro;          //来自何地
    private String User_gradeid;            //用户等级ID
    private String User_mana;            //用户威望值
    private String User_ex;          //用户经验值
    //private String User_money;           //用户金币
    //private String User_job;           //用户论坛职务
    private String User_honorid;              //用户勋章ID
    private String User_show;         //用户个性签名
    private String User_time;         //用户注册日期
    private String User_purid;          //用户权限组ID
}
