package com.boki.bokifeign.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description: 状态码、信息、数据
 */
@Data
@Accessors(chain = true)
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data = null;
}
