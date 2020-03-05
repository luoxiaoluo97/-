package com.boki.bokiapi.entity.vo.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description: 帖子详情
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostDetailVO {
    /**
     * 帖子属性，包括了1楼
     */
    private Long id;                       //帖子id
    private String title;                  //标题
    private Long userId;                   //楼主id
    private Long repliesCount;             //回贴数
    private String type;                   //帖子类型，精品贴或其他
    private ArrayList<ReplyVO> storeys;    //楼层列表
}
