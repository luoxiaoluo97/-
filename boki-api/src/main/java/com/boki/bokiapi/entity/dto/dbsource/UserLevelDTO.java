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
public class UserLevelDTO {
    private Long exp;        //等级
    private Integer level;      //所需经验值
}
