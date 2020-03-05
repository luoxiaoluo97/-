package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/3
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class StoreyReplySendDTO {

    private Long userId;                //回复者id

    @NotNull(message = StatusName.NULL_STOREY_ID)
    private Long storeyId;              //所属楼层

    @NotNull(message = StatusName.NULL_CONTENT)
    private String content;             //回复内容
}
