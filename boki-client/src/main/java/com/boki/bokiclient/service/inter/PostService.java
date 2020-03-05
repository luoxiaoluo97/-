package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.dto.request.PostSendDTO;
import com.boki.bokiapi.entity.dto.request.ReplySendDTO;
import com.boki.bokiapi.entity.dto.request.StoreyReplySendDTO;

public interface PostService {

    /**
     * 发帖
     * @param postSendDTO
     * @return
     */
    int sendPost(PostSendDTO postSendDTO);

    /**
     * 回帖
     */
    int sendReply(ReplySendDTO replySendDTO);

    /**
     * 回复层主
     */
    int sendStoreyReply(StoreyReplySendDTO dto);

    /**
     * 删帖
     */
    int deletePost(PostDTO dto);

    /**
     * 删楼
     */
    int deleteReply(ReplyDTO dto);

    /**
     * 删楼中楼
     */
    int deleteStoreyReply(StoreyReplyDTO dto);
}
