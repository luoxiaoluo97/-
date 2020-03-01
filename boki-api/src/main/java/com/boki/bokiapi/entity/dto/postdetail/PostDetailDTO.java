package com.boki.bokiapi.entity.dto.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostDetailDTO {
    /**
     * 帖子属性，包括了1楼
     */
    private Long id;                            //帖子id
    private Long userId;                        //楼主id
    private String title;                       //标题
    private String content;                     //内容
    private String createTime;                  //发帖时间
    private Long repliesCount;                  //回复数
    private Integer typeId;                        //帖子类型，精品贴或其他
    /**
     * 楼主属性
     */
    private String userName;                    //楼主昵称
    private String photo;                       //楼主头像
    private Long exp;                           //楼主经验值
    private Integer creditDegree;                //用户信用度
    private Integer roleId;                     //论坛角色

    private ArrayList<ReplyDTO> replies;        //回复列表
}
