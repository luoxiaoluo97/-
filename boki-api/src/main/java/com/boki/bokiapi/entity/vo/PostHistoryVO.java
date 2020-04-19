package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 历史发帖
 */
@Data
@Accessors(chain = true)
public class PostHistoryVO {

    private Long id;                    //帖子id
    private String title;               //标题
    private String content;             //内容
    private Long repliesCount;          //回复数
    private Long floorNo;           //第x楼
    private String type;                //帖子类型，精品贴或其他

    private String createTime;          //发帖时间
}
