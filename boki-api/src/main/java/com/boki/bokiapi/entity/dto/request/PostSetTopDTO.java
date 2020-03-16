package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Data
@Accessors(chain = true)
public class PostSetTopDTO {

    private Long uId;           //操作者id

    @NotNull(message = StatusName.NULL_POST_ID)
    private Long postId;        //帖子id

    @Min(value = 1,message = StatusName.ERROR_RANGE)
    private Integer topDays;    //置顶持续时间

    private String topUntil;
}
