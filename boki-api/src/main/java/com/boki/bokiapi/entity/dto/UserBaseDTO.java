package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserBaseDTO {
    private Long id;                    //用户ID
    private String mail;                //邮箱即是账号
    private String userName;            //昵称
    private Long exp;                   //用户经验值
    private Integer creditDegree;       //用户信用度
    private String banUntil;            //解封日期
}
