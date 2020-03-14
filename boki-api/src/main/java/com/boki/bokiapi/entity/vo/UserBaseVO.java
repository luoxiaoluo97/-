package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserBaseVO {
    private Long id;                    //用户ID
    private String mail;                //邮箱即是账号
    private String userName;            //昵称
    private Integer level;              //等级
    private Integer honorId;            //信誉勋章
    private String banUntil;            //解封日期
}
