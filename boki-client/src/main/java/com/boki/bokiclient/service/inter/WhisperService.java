package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.WhisperVO;

public interface WhisperService {

    /**
     * 打开私信会话
     */
    WhisperVO openWhisper(Long firstUserId, Long secondUserId);


    /**
     * 发送私信
     */
    Integer sendWhisper(WhisperSendDTO dto);

    /**
     * 私信列表
     */
    DataWithTotal getWhisperList(Long userId,Integer page);

    /**
     * 从列表移除私信
     */
    Integer removeWhisper(Long userId,Integer whisperId);
}
