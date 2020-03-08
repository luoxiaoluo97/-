package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.dto.request.PostSendDTO;
import com.boki.bokiapi.entity.dto.request.ReplySendDTO;
import com.boki.bokiapi.entity.dto.request.StoreyReplySendDTO;
import com.boki.bokiapi.entity.po.PostPO;
import com.boki.bokiapi.entity.po.ReplyPO;
import com.boki.bokiapi.entity.po.StoreyReplyPO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
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
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional
    public int sendPost(PostSendDTO dto) {

        //防灌水，15秒内禁止重复发帖
        String waiting = Common.WAITING + dto.getUserId();
        if (!redisTemplate.hasKey(waiting) ) {
            redisTemplate.opsForValue().set(waiting,"");
            redisTemplate.expire(waiting, 15, TimeUnit.SECONDS);
            PostPO po = new PostPO();
            BeanUtils.copyProperties(dto, po);
            //最后回复者默认楼主
            po.setLastReplierId(dto.getUserId());
            int count = postDao.insertPost(po);
            // TODO 需要实现@机制PostSendDTO
            if (count == 1){
                postDao.insertFirstReply(po);
                //经验+5,告辞
                postDao.updateExpById(dto.getUserId(), 5);
            }else {
                throw new BusinessException("发帖更新"+count+"异常").setType(RequestResultCode.SERVER_ERROR);
            }
            return count;
        }else {
            return -1;
        }
    }



    @Override
    @Transactional
    public int sendReply(ReplySendDTO dto) {
        //防灌水，15秒内禁止重复发帖
        String waiting = Common.WAITING + dto.getUserId();
        if (!redisTemplate.hasKey(waiting) ) {
            redisTemplate.opsForValue().set(waiting, "");
            redisTemplate.expire(waiting, 15, TimeUnit.SECONDS);
            ReplyPO po = new ReplyPO();
            BeanUtils.copyProperties(dto,po);
            int count = postDao.insertReply(po);
            // TODO 还需实现@机制
            //经验结算，楼主回复自己的贴,经验+2
            if(postDao.findPostByIdAndUser(dto.getPostId(),dto.getUserId()) != 0){
                postDao.updatePosterSendReply(po);
            //非楼主回帖，帖子回帖数更新，楼主经验+1，层主经验+3
            }else{
                postDao.updateReplierSendReply(po);
            }
            return count;
        }else {
            return -1;
        }
    }

    @Override
    @Transactional
    public int sendStoreyReply(StoreyReplySendDTO dto) {
        //防灌水，15秒内禁止重复发帖
        String waiting = Common.WAITING + dto.getUserId();
        if (!redisTemplate.hasKey(waiting) ) {
            redisTemplate.opsForValue().set(waiting, "");
            redisTemplate.expire(waiting, 15, TimeUnit.SECONDS);
            StoreyReplyPO po = new StoreyReplyPO();
            BeanUtils.copyProperties(dto,po);
            int count = postDao.insertStoreyReply(po);
            // TODO 还需实现@机制
            if(postDao.isFirstFloor(po.getStoreyId()) == 1){
                throw new BusinessException("错误的楼层回复：1楼").setType(RequestResultCode.SERVER_ERROR);
            }
            //经验结算
            if(postDao.findReplyByIdAndUser(po.getStoreyId(),po.getUserId()) == 1){
                postDao.expSettlementByReplier(po);
            }else {
                postDao.expSettlementByOther(po);
            }
            return count;
        }else {
            return -1;
        }
    }

    @Override
    public int deletePost(PostDTO dto) {
        PostPO po = new PostPO();
        BeanUtils.copyProperties(dto,po);
        int count = postDao.deletePost(po);
        return count;
    }

    @Override
    public int deleteReply(ReplyDTO dto) {
        ReplyPO po = new ReplyPO();
        BeanUtils.copyProperties(dto,po);
        int count = postDao.deleteReply(po);
        return count;
    }

    @Override
    public int deleteStoreyReply(StoreyReplyDTO dto) {
        StoreyReplyPO po = new StoreyReplyPO();
        BeanUtils.copyProperties(dto,po);
        int count = postDao.deleteStoreyReply(po);
        return count;
    }



}
