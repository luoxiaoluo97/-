package com.boki.bokiapi.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/10
 * @Description:
 */
@Data
@Accessors(chain = true)
public class NoticePO {
    private Integer id;
    private Long userId;                //被通知用户
    private String notice;              //通知内容
    private Integer typeId;             //通知类型
    private Long postId;                //帖子id

    private String createTime;
    private String modifiedTime;
    private String creator;             //通知来源
    private String modifier;
    private String isDeleted;
}
