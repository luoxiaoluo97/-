package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;

import java.util.ArrayList;

public interface CommonService {

    /**
     * 帖子详情
     * @param postId
     * @return
     */
    public PostDetailVO getPostDetail(Long postId,Integer page);

    /**
     * 加载楼中楼
     */
    public ArrayList<StoreyReplyVO> findStoreyReplyById(Long replyId,Integer page);
}
