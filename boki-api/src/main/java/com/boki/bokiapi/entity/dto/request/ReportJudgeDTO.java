package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author: LJF
 * @Date: 2020/3/15
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ReportJudgeDTO {
    @NotNull(message = StatusName.NULL_TYPE)
    @Pattern(regexp = "post|reply|storeyReply",message = StatusName.ERROR_TYPE)
    private String type;           //帖子post、回复reply、楼层回复storeyReply

    @NotNull(message = StatusName.NULL_POST_ID)
    private Long id;               //帖子、回复或二级回复id

    @NotNull(message = StatusName.NULL_TARGET_USER)
    private Long userId;          //所属用户

    private String reason;    //举报理由

}
