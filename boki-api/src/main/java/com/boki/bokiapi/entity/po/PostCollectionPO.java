package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description: 帖子收藏
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostCollectionPO {
    private Long id;                //id
    private Long userId;            //收藏者id
    private Long postId;            //帖子id
    private String title;           //帖子标题

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
