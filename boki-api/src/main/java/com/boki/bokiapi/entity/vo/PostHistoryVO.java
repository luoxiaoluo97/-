package com.boki.bokiapi.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 历史发帖
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostHistoryVO {

    private Long id;                    //帖子id
    private String title;               //标题
    private String content;             //内容
    private Long repliesCount;          //回复数
    private String type;                //帖子类型，精品贴或其他

    private String createTime;          //发帖时间
}
