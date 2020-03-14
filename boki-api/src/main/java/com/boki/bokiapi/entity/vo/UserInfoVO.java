package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:个人信息页面
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserInfoVO {
    private Long id;                    //用户ID
    private String mail;                //邮箱即是账号
    private String pwd;                 //密码
    private String userName;            //昵称
    private String photo;               //头像
    private String sex;                 //用户性别,保密，男，女
    private String birth;               //出生日期
    private String comeFrom;            //来自何地
    private Integer level;              //等级
    private Long exp;                   //用户经验值
    private Integer creditDegree;        //用户信用度
    private Integer honorId;            //荣誉id
    private Integer roleId;              //论坛角色
    private String role;                //角色，职务
    private String intro;               //自我简介
    private String show;                //用户个性签名
    private Integer postAmount;            //发帖数
    private String isBanned;            //是否禁封
    private String banUntil;            //解封日期
}
