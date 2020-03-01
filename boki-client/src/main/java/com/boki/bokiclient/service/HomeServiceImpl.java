package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.vo.HomeVO;
import com.boki.bokiapi.entity.vo.PostVO;
import com.boki.bokiclient.bean.DBSourceSelectBean;
import com.boki.bokiclient.dao.HomeDao;
import com.boki.bokiclient.service.inter.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description:
 */
@Slf4j
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeDao homeDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public HomeVO findPosts(Integer page) {
        ArrayList<PostDTO> posts = homeDao.findPosts((page-1)*30,30);
        Long postsCount = homeDao.getPostsCount();
        ArrayList<PostVO> postVOs = new ArrayList<>();
        //复制List属性
        if( posts != null) {
            for (int i = 0; i < posts.size(); i++) {
                postVOs.add(new PostVO());
                BeanUtils.copyProperties(posts.get(i), postVOs.get(i));
                postVOs.get(i).setLastReplyTime(posts.get(i).getModifiedTime());
                //根据帖子类型id赋予类型名称
                String postType = dbSource.getType(posts.get(i).getTypeId());
                postVOs.get(i).setType(postType);
            }
        }
        return new HomeVO().setPosts(postVOs).setPostsCount(postsCount);
    }
}
