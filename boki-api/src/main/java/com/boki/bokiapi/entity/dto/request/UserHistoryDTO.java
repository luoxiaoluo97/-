package com.boki.bokiapi.entity.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserHistoryDTO {

    private Long userId;            //用户id

    private Integer page;           //页数

    /**
     * 浏览方式：查看所有，只看管理员删帖
     */
    private Integer mode;

    private Integer start;
    private Integer end;
}
