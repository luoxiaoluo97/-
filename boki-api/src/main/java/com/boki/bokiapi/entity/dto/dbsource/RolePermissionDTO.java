package com.boki.bokiapi.entity.dto.dbsource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/1
 * @Description:
 */

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RolePermissionDTO {
    private Integer id;             //角色id
    private String role;            //角色
    private String permission;      //接口权限口令
}
