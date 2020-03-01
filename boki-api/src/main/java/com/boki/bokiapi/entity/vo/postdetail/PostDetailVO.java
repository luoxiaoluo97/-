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
    private String content;                //内容
    private Long userId;                        //楼主id
    private String photo;                       //楼主头像
    private String userName;                    //楼主昵称
    private Long exp;                           //楼主经验值
    private Integer level;                      //楼主等级
    private Integer creditDegree;                //用户信用度
    private Integer honorId;                    //荣誉id
    private String createTime;                  //发帖时间
    private String role;                        //论坛角色
    private Long repliesCount;             //回贴数
    private String type;                   //帖子类型，精品贴或其他
    private ArrayList<ReplyVO> storeys;    //楼层列表
}
