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
public class ReplySendDTO {

    private Long userId;            //层主id
    private Long floorNo = 0L;      //第x楼

    private Long postId;            //所属帖子

    private String content;         //回复内容

    private String shortContent;         //缩略内容，无html
}
