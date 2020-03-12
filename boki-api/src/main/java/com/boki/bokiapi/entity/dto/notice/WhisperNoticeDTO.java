package com.boki.bokiapi.entity.dto.notice;

import com.boki.bokiapi.value.notice.NoticePrefix;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperNoticeDTO {
    private String userName;            //你
    private String forUID;              //回复了谁

    private NoticePrefix pre;           //通知类型
}
