package com.boki.bokiapi.entity.dto.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ReplyDTO {
    /**
     * 楼层属性
     */

    private Long id;                //楼层id
    private Long userId;            //层主id
    private Long postId;            //所属帖子
    private Long floorNo;           //第x楼
    private String createTime;      //回复时间
    private String content;         //回复内容
    private Long repliesCount;      //楼层回复数
    /**
     * 层主属性
     */
    private String userName;        //层主昵称
    private String photo;           //层主头像
    private Long exp;               //层主经验值
    private Integer creditDegree;    //层主信用度
    private Integer roleId;         //论坛角色

}
