package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperSendDTO {

    private Integer whisperId;          //所属对话

    private Long userId;                //私信方

    @NotNull(message = StatusName.NULL_TARGET_USER)
    private Long targetUserId;          //接收方

    @NotNull(message = StatusName.NULL_CONTENT)
    private String content;             //私信内容

}
