package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostVO {

    private Long id;                //帖子id
    private String userName;        //楼主昵称
    private Long creditDegree;      //楼主信用度,用于获取信用勋章id
    private String title;           //标题
    private String content;         //内容
    private String createTime;      //发帖时间
    private String lastReplier;     //最后回复者
    private String lastReplyTime;   //最后回复时间
    private Long repliesCount;      //回复数
    private String type;            //帖子类型，精品贴或其他
    private String isTop;          //是否置顶贴
}
