package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class PostTypePO {
    private Integer id;
    private String type;                //帖子类型

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;

}
