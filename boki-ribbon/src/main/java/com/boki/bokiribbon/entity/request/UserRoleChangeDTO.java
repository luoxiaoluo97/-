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
public class UserRoleChangeDTO {

    private Long id;      //站长id

    private Long userId;        //被改动用户

    private String roleId;         //将要赋予的职位

}
