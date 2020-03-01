package com.boki.bokiapi.entity.dto.dbsource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostTypeDTO {
    private Integer id;
    private String type;                //帖子类型

}
