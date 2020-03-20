package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Data
@Accessors(chain = true)
public class PostSetTopDTO {

    private Long uId;           //操作者id

    private Long postId;        //帖子id

    private Integer topDays;    //置顶持续时间

    private String topUntil;
}
