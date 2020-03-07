package com.boki.bokiclient.service.inter;


import com.boki.bokiapi.entity.vo.DataWithTotal;

public interface HomeService {

    /**
     * 获取帖子列表
     * @return
     */
    public DataWithTotal findPosts(Integer page);
}
