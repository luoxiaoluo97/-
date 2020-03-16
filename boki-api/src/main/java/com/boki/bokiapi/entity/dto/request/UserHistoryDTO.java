package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/14
 * @Description:
 */
@Data
@Accessors(chain = true)
public class UserHistoryDTO {

    @NotNull(message = StatusName.NULL_TARGET_USER)
    private Long userId;            //用户id

    @NotNull(message = StatusName.NULL_PAGE)
    private Integer page;           //页数

    /**
     * 浏览方式：查看所有，只看管理员删帖
     */
    @NotNull(message = StatusName.NULL_ONLY_DELETED)
    @Pattern(regexp = "1|2",message = StatusName.ERROR_MODE)
    private String mode;

    private Integer start;
    private Integer end;
}
