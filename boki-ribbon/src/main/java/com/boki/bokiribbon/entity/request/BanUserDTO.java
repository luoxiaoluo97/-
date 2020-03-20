package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class BanUserDTO {

    private Long userId;            //禁封角色

    private Integer days;           //禁封天数

    private String banUntil;        //解封日期
}
