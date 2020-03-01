package com.boki.bokiapi.entity.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:个人信息页面
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserInfoVO {
    private Long id;
    private String mail;                        //邮箱即是账号
    private String userName;                    //昵称
    private String photo;                       //头像
    private String age;                         //年龄
    private Long sex;                           //用户性别,保密，男，女
    private String birth;                       //出生日期
    private String blog;                        //用户主页
    private String from;                        //来自何地
//    private String gradeId;                     //用户等级ID
    private String exp;                         //用户经验值
    private Integer creditDegree;                //用户信用度
    private String roleId;                      //论坛角色
//    private String honorId;                     //用户信用度勋章ID
    private String intro;                       //自我简介
    private String show;                        //用户个性签名
    private String createTime;                  //用户注册日期
    private ArrayList<JSONObject> collection;   //收藏列表
    private Long postAmount;                    //发帖数
    private String isBanned;                    //是否禁封
}
