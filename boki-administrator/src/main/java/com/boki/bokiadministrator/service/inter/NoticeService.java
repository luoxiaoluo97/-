package com.boki.bokiadministrator.service.inter;

import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description: 通知
 */
public interface NoticeService {

    /**
     * 给被举报者发送删帖通知
     * @param msg
     * @param elem
     */
    void sendReportPassNotice(NoticeMessage msg, NoticeElem elem);

}
