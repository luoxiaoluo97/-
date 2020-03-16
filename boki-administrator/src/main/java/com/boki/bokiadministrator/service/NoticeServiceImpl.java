package com.boki.bokiadministrator.service;

import com.boki.bokiadministrator.dao.NoticeDao;
import com.boki.bokiadministrator.service.inter.NoticeService;
import com.boki.bokiapi.entity.po.NoticePO;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private NoticeDao noticeDao;


    /**
     * 通知用户id关键字
     */
    public static final String NOTIFY_BY_UID = "NOTIFY_BY_ID";


    @Override
    public void sendReportPassNotice(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            // 根据类型获取帖子id。还有 title 或 reply 或 storeyReply
            Map map = msg.getType().intValue() == 4 ? noticeDao.findPostTitle(elem.getId()) :
                    msg.getType().intValue() == 5 ? noticeDao.findReplyContent(elem.getId()) :
                            noticeDao.findStoreyReplyContent(elem.getId());
            //不管有没有，属性都给装上
            elem.setPostId( (Long)map.get("post_id"))
                    .setTitle( (String)map.get("title"))
                    .setReply( (String)map.get("reply"))
                    .setStoreyReply( (String)map.get("storey_reply"));
            NoticePO po = new NoticePO()
                    .setUserId(elem.getTargetUserId())
                    .setCreator(elem.getFromUserId().toString())
                    .setNotice( msg.createNotice(elem))
                    .setTypeId(msg.getType());
            //持久化一条通知
            noticeDao.insertNotice(po);
            //设置通知数
            setKeyValueByUid(elem.getTargetUserId());
        }).start();
    }


    /**
     * 通过userId设置消息数
     * @param userId
     */
    private void setKeyValueByUid(Long userId){
        String userKey = NOTIFY_BY_UID +userId;
        if ((!redisTemplate.hasKey(userKey))) {
            redisTemplate.opsForValue().set(userKey, "1");
        } else {
            redisTemplate.opsForValue().increment(userKey, 1);
        }
    }


}
