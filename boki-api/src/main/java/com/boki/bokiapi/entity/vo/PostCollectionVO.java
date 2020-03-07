package com.boki.bokiapi.entity.vo;

import lombok.Data;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */
@Data
public class PostCollectionVO {
    private Long id;                //id
    private Long postId;            //帖子id
    private String title;           //帖子标题
}
