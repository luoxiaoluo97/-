package com.boki.bokiapi.entity.dto;

import lombok.Data;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */
@Data
public class PostCollectionDTO {
    private Long id;                //id
    private Long postId;            //帖子id
    private String title;           //帖子标题
}
