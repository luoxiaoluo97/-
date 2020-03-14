package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description: 管理端查询请求
 */
@Data
@Accessors(chain = true)
public class UserSelectDTO {

    private String elem;                 //模糊查询，昵称,邮箱

    @Max(value = 10,message = StatusName.ERROR_RANGE)
    @Min(value = 1,message = StatusName.ERROR_RANGE)
    private Integer maxLevel;            //小于等级

    @Max(value = 10,message = StatusName.ERROR_RANGE)
    @Min(value = 1,message = StatusName.ERROR_RANGE)
    private Integer minLevel;            //大于等级


    private Long maxExp;                 //小于经验值
    private Long minExp;                 //大于经验值

    @Max(value = 150,message = StatusName.ERROR_RANGE)
    @Min(value = 0,message = StatusName.ERROR_RANGE)
    private Integer maxCreditDegree;     //小于信用度

    @Max(value = 150,message = StatusName.ERROR_RANGE)
    @Min(value = 0,message = StatusName.ERROR_RANGE)
    private Integer minCreditDegree;     //大于信用度

    @Pattern(regexp = "n|y",message = StatusName.ERROR_IS_BANNED)
    private String isBanned;             //是否禁封

    @NotNull(message = StatusName.NULL_PAGE)
    private Integer page;               //页数

    private Integer start;              //起始值
    private Integer end;                //结束值

}