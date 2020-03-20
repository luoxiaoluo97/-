package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/15
 * @Description:
 */
@Data
@Accessors(chain = true)
public class ReportJudgeDTO {
    private String type;           //帖子post、回复reply、楼层回复storeyReply

    private Long id;               //帖子、回复或二级回复id

    private Long userId;          //所属用户

    private String reason;    //举报理由

}
