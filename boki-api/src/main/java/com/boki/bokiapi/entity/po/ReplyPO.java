package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 帖子的回复，即楼层
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ReplyPO {

    private Long id;                //楼层id
    private Long postId;            //所属帖子
    private Long floorNo;           //第x楼
    private Long userId;            //层主id
    private String content;         //回复内容
    private String shortContent;    //无html的内容
    private Integer reportStatus;       //举报状态
    private String reportReason;        //举报理由

    private String createTime;      //回复时间
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
