package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/3
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class StoreyReplySendDTO {

    private Long userId;                //回复者id

    private Long storeyId;              //所属楼层

    private String content;             //回复内容
}
