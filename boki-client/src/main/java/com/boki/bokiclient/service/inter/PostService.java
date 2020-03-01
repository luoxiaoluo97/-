package com.boki.bokiclient.service.inter;

import com.boki.bokiapi.entity.dto.PostDTO;

public interface PostService {

    /**
     * 发帖
     * @param postDTO
     * @return
     */
    int insertPost(PostDTO postDTO);

    /**
     * 删帖
     */
    int deletePost(PostDTO postDTO);
}
