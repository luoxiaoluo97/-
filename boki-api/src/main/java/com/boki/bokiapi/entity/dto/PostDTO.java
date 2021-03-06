package com.boki.bokiapi.entity.dto;

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
public class PostDTO {

    private Long id;                //帖子id
    private Long userId;            //楼主id
    private String userName;        //楼主昵称
    private Integer hostCreditDegree;              //楼主信用度,用于获取勋章id
    private String title;           //标题
    private String content;         //内容
    private String shortContent;         //缩略内容
    private Integer typeId;         //类型id
    private String type;            //类型
    private String isTop;           //是否置顶
    private Integer reportStatus;       //举报状态
    private String reportReason;        //举报理由

    private String createTime;      //发帖时间
    private String lastReplier;     //最后回复者
    private Integer lastReplierCreditDegree;   //新增===最后回复者主信用度,用于获取勋章id
    private String modifiedTime;    //最后回复时间
    private Long repliesCount;      //回复数

    private Integer totalCount;     //发帖总数

}
