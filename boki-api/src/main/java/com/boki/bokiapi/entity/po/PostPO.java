package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 帖子
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostPO {

    private Long id;                    //帖子id
    private Long userId;                //楼主id
    private String title;               //标题
    private String content;             //内容
    private Long lastReplierId;         //最后回复者id
    private Long repliesCount;          //回复数
    private Integer typeId;                   //帖子类型，精品贴或其他
    private String isTop;               //是否置顶贴

    private String createTime;          //发帖时间
    private String modifiedTime;        //最后回复时间
    private String creator;
    private String modifier;
    private String isDeleted;
}
