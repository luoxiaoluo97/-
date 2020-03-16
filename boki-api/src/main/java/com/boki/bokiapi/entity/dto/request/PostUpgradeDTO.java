package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/16
 * @Description:
 */
@Data
@Accessors(chain = true)
public class PostUpgradeDTO {

    private Long uId;           //操作者id

    @NotNull(message = StatusName.NULL_POST_ID)
    private Long postId;        //帖子id

    @NotNull(message = StatusName.NULL_TYPE)
    @Pattern(regexp = "1|2",message = StatusName.ERROR_TYPE)
    private String typeId;      //帖子类型

}
