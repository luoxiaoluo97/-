package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {

    private Integer id;            //用户ID
    private String mail;		//邮箱即是账号
    private String pwd;          //密码
    private String userName;          //昵称
    private String photo;           //头像
    private String sex;          //用户性别,保密，男，女
    private String birth;          //出生日期
//    private String blog;          //用户主页
    private String comeFrom;          //来自何地
    private String gradeId;            //用户等级ID
    private String exp;            //用户经验值
    private String creditDegree;          //用户信用度
    //private String gold;           //用户金币
    private String jobId;           //用户论坛职务
    private String honorId;              //用户信用度勋章ID
    private String intro;           //自我简介
    private String show;         //用户个性签名
//    private String purid;          //用户权限组ID
    private String collection;      //收藏列表
    private String createTime;         //创建时间
    private Long postAmount;           //发帖数

}
