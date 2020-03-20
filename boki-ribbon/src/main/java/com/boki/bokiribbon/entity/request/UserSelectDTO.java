package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description: 管理端查询请求
 */
@Data
@Accessors(chain = true)
public class UserSelectDTO {

    private String elem;                 //模糊查询，昵称,邮箱

    private Integer maxLevel;            //小于等级

    private Integer minLevel;            //大于等级


    private Long maxExp;                 //小于经验值
    private Long minExp;                 //大于经验值

    private Integer maxCreditDegree;     //小于信用度

    private Integer minCreditDegree;     //大于信用度

    private String isBanned;             //是否禁封

    private Integer page;               //页数

    private Integer start;              //起始值
    private Integer end;                //结束值

}