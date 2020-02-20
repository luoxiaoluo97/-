package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:数据校验
 */
@Data
@Accessors(chain = true)
public class ExceptionResultVO<T> {
    private Integer code;
    private String msg;
}
