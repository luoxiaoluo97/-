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
public class UserHonorDTO {
    private Integer id;             //荣誉id
    private Integer creditDegree;   //所需信誉度等级
}
