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
    private String shortContent;            //短内容
    private Long repliesCount;              //回复数
    private String createTime;              //发帖时间
    private String typeId;                //帖子类型，精品贴或其他
}
