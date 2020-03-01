package com.boki.bokiclient.service.inter;


import com.boki.bokiapi.entity.vo.HomeVO;

public interface HomeService {

    /**
     * 获取帖子列表
     * @return
     */
    public HomeVO findPosts(Integer page);
}
