package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.po.PostPO;
import com.boki.bokiclient.dao.PostDao;
import com.boki.bokiclient.service.inter.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @Author: LJF
 * @Date: 2020/2/28
 * @Description:
 */
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int insertPost(PostDTO postDTO) {
        // TODO 需要实现@机制
        //防灌水，15秒内禁止重复发帖
        String waiting = "waiting"+postDTO.getUserId();
        if (!redisTemplate.hasKey(waiting) ) {
            redisTemplate.opsForValue().set(waiting,"");
            redisTemplate.expire(waiting, 15, TimeUnit.SECONDS);
            PostPO postPO = new PostPO();
            BeanUtils.copyProperties(postDTO, postPO);
            //最后回复者默认楼主
            postPO.setLastReplierId(postDTO.getUserId());
            int count = postDao.insertPost(postPO);
            //经验+5,告辞
            postDao.updateExpById(postDTO.getUserId(), 5);
            return count;
        }else {
            return -1;
        }
    }

    @Override
    public int deletePost(PostDTO postDTO) {
        PostPO postPO = new PostPO();
        BeanUtils.copyProperties(postDTO,postPO);
        int count = postDao.deletePost(postPO);
        return count;
    }
}
