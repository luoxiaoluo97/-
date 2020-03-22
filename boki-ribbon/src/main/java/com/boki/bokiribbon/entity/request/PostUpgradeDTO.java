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
public class PostUpgradeDTO {

    private Long uId;           //操作者id

    private Long postId;        //帖子id

    private String typeId;      //将要成为的帖子类型,1普通，2精品

}
