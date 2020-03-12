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

    /**
     * 拉黑
     */
    Integer addBlacklist(Long userId,Long targetUserId);

    /**
     * 黑名单列表
     */
    DataWithTotal getBlacklist(Long userId, Integer page);

    /**
     * 移出黑名单
     */
    Integer removeBlacklist(Long userId,Integer blacklistId);
}
