package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Data
@Accessors(chain = true)
public class SummaryVO {
    private Integer userCount;          //用户数
    private Integer postCount;          //帖子总数,不计回复
    private Integer deletedCount;       //删帖数
    private Float averagePost;        //人均发帖
    private Integer monthlyPosting;     //月发帖量
}
