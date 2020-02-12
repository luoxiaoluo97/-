package com.boki.bokiapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "boki_user")
public class User {
    @Id
    private ObjectId id;            //用户ID
    private String account;         //账号
    private String pwd;          //密码
    private String userName;          //用户名
    private String photo;           //头像
    private String age;             //年龄
    private String passQuestion;          //密码提示问题
    private String passAnswer;          //密码提示问题答案
    private String sex;          //用户性别
    private String birth;          //出生日期
    private String blog;          //用户主页
    private String from;          //来自何地
    private String gradeid;            //用户等级ID
    private String exp;            //用户经验值
    private String creditDegree;          //用户信用度
//    //private String gold;           //用户金币
//    //private String job;           //用户论坛职务
    private String honorid;              //用户信用度勋章ID
    private String show;         //用户个性签名
    private String time;         //用户注册日期
    private String purid;          //用户权限组ID
}
