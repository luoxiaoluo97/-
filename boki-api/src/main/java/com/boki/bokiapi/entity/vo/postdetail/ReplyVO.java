package com.boki.bokiapi.entity.vo.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description: 楼层信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ReplyVO {
    /**
     * 层主属性
     */
    private Long userId;                           //层主id
    private String userName;                       //层主昵称
    private String photo;                           //层主头像
    private Long exp;                           //用户经验值
    private Integer level;                         //层主等级
    private Integer creditDegree;                   //用户信用度
    private Integer honorId;                       //荣誉id
    private String role;                           //论坛角色
    private String show;                          //个性签名
    /**
     * 楼层属性
     */
    private Long id;                //楼层id
    private Long postId;            //所属帖子
    private Long floorNo;           //第x楼
    private String createTime;      //回复时间
    private String content;         //回复内容
    private Long repliesCount;      //楼层回复数

}
