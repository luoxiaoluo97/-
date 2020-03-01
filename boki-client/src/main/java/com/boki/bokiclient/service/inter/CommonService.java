package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;

public interface CommonService {

    /**
     * 帖子详情
     * @param postId
     * @return
     */
    public PostDetailVO getPostDetail(Long postId);
}
