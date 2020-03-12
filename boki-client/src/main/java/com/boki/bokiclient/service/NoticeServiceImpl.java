package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.po.NoticePO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.NoticeVO;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import com.boki.bokiclient.dao.NoticeDao;
import com.boki.bokiclient.dao.PostDao;
import com.boki.bokiclient.dao.UserDao;
import com.boki.bokiclient.service.inter.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private UserDao userDao;

    @Autowired
    private PostDao postDao;

    /**
     * 通知用户id关键字
     */
    public static final String NOTIFY_BY_UID = "NOTIFY_BY_ID";

    /**
     * 通知用户名关键字
     */
    public static final String NOTIFY_BY_USERNAME = "NOTIFY_BY_USERNAME";

    @Override
    public void sendWhisperNotice(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            //通知者 == 创建者
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

    @Override
    public void sendNoticeToFans(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            //首先要找到你的粉丝
            List<Long> fans = userDao.findAllFans(elem.getFromUserId());
            if (fans.size() > 0) {
                //找到你刚发的贴
                Long postId = noticeDao.findLastPostId(elem.getFromUserId(),elem.getTitle());
                List<NoticePO> noticePOs = new ArrayList();
                for (Long fan : fans) {
                    noticePOs.add(
                            new NoticePO().setCreator(elem.getFromUserId().toString())
                                    .setNotice(msg.createNotice(elem))
                                    .setUserId(fan)
                                    .setPostId(postId)
                                    .setTypeId(msg.getType())
                    );
                }
                noticeDao.multiInsertNotice(noticePOs);
                //为粉丝设置通知数
                for (Long fan : fans) {
                    setKeyValueByUid(fan);
                }
            }
        }).start();
    }

    @Override
    public void sendReplyNotice(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            // 需要 title、 楼层 、楼主id
            Map map = noticeDao.findReplyNoticeElem(elem.getFromUserId(),elem.getContent());
            elem.setTitle(map.get("title").toString())
                    .setFloorNo(map.get("floor_no").toString())
                    .setTargetUserId((Long)map.get("user_id"));
            //如果是回复自己的贴则不需要通知
            if ((long)elem.getFromUserId() != (long)elem.getTargetUserId()) {
                noticeDao.insertNotice(
                        new NoticePO().setTypeId(msg.getType())
                                .setCreator(elem.getFromUserId().toString())
                                .setNotice(msg.createNotice(elem))
                                .setPostId(elem.getPostId())
                                .setUserId(elem.getTargetUserId())
                );
                setKeyValueByUid(elem.getTargetUserId());
            }
        }).start();
    }


    @Override
    public void sendStoreyReplyNotice(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            //需要获取帖子id、 title、 楼层、层主id，帖子id用于日后构造帖子链接，通知属于层主id，其余的用于生成通知文本。
            Map map = noticeDao.findStoreyReplyNoticeElem(elem.getStoreyId());
            elem.setTitle(map.get("title").toString())
                    .setFloorNo(map.get("floor_no").toString())
                    .setTargetUserId((Long)map.get("user_id"))
                    .setPostId((Long)map.get("post_id"));
            //如果是回复自己的楼层则不需要通知
            if ((long)elem.getFromUserId() != (long)elem.getTargetUserId()) {
                noticeDao.insertNotice(
                        new NoticePO().setTypeId(msg.getType())
                                .setCreator(elem.getFromUserId().toString())
                                .setNotice(msg.createNotice(elem))
                                .setPostId(elem.getPostId())
                                .setUserId(elem.getTargetUserId())
                );
                setKeyValueByUid(elem.getTargetUserId());
            }
        }).start();
    }

    @Override
    public void sendNoticeWhenGotAReply(NoticeMessage msg, NoticeElem elem) {
        new Thread(() -> {
            Long targetUserId = getReceiverFromContent(elem.getContent(),elem.getFromUserName());
            // 用户存在则通知
            if (targetUserId != null){
                // 需要帖子id、 title、 第n楼, 这个帖子id用于日后构造帖子链接，其余的用于生成通知文本。
                Map map = noticeDao.findStoreyReplyNoticeElem(elem.getStoreyId());
                elem.setTitle(map.get("title").toString())
                        .setFloorNo(map.get("floor_no").toString())
                        .setPostId((Long)map.get("post_id"));
                NoticePO po = new NoticePO()
                        .setTypeId(msg.getType())
                        .setCreator(elem.getFromUserId().toString())
                        .setNotice(msg.createNotice(elem))
                        .setPostId(elem.getPostId())
                        .setUserId(targetUserId);
                noticeDao.insertNotice(po);
                setKeyValueByUid(targetUserId);
            }
        }).start();
    }

    @Override
    public void sendNoticeByAt(NoticeMessage msg, NoticeElem elem,Integer where) {
        new Thread(() -> {
            List<Long> userIdList = getAtIdFromContent(elem.getContent(),elem.getFromUserName(),where);
            if (userIdList != null && userIdList.size() > 0) {
                Map map;
                if(where == 1) {
                    //属于楼中楼回复 需要帖子id、 title、 第n楼, 这个帖子id用于日后构造帖子链接，其余的用于生成通知文本。
                    map = noticeDao.findStoreyReplyNoticeElem(elem.getStoreyId());
                    elem.setTitle(map.get("title").toString())
                            .setFloorNo(map.get("floor_no").toString())
                            .setPostId((Long) map.get("post_id"));
                }else {
                    //属于楼层回复 需要 title、 第n楼, 这个帖子id用于日后构造帖子链接，其余的用于生成通知文本。
                    map = noticeDao.findReplyNoticeElem(elem.getFromUserId(),elem.getContent());
                    elem.setTitle(map.get("title").toString())
                            .setFloorNo(map.get("floor_no").toString());
                }
                List<NoticePO> pos = new ArrayList<>();
                for (Long id : userIdList){
                    pos.add( new NoticePO()
                            .setTypeId(msg.getType())
                            .setCreator(elem.getFromUserId().toString())
                            .setNotice(msg.createNotice(elem))
                            .setPostId(elem.getPostId())
                            .setUserId(id));
                }
                noticeDao.multiInsertNotice(pos);
                setKeyValueByUid(userIdList);
            }
        }).start();
    }

    @Override
    public Integer getNoticeCount(Long userId) {
        String key = NOTIFY_BY_UID +userId;
        if( redisTemplate.hasKey(key) ){
            Object o = redisTemplate.opsForValue().get(key);
            return Integer.valueOf(o.toString());
        }
        return 0;
    }

    @Override
    public DataWithTotal getNoticeList(Long userId, Integer page) {
        List<List<?>> result = noticeDao.findNoticeByUid(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result, NoticeVO.class);
        // 打开通知列表后，清空通知数量
        redisTemplate.delete(NOTIFY_BY_UID+userId);
        return vo;
    }

    @Override
    public Integer removeNotice(Long userId, Integer id) {
        return noticeDao.deleteNoticeById(userId,id);
    }

    @Override
    public Integer clearNotice(Long userId) {
        return noticeDao.deleteAllNotice(userId);
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

    /**
     * 通过userId 列表设置消息数
     * @param userIdList
     */
    private void setKeyValueByUid(List<Long> userIdList){
        for (Long userId : userIdList ) {
            String userKey = NOTIFY_BY_UID +userId;
            if ((!redisTemplate.hasKey(userKey))) {
                redisTemplate.opsForValue().set(userKey, "1");
            } else {
                redisTemplate.opsForValue().increment(userKey, 1);
            }
        }
    }

    /**
     * 是否楼中楼回复格式，以正则开头     回复[ ]@[^@ ]{1,12}[ ]
     * @param fromUserName 用于排除自己
     * @return 返回用户id
     */
    private Long getReceiverFromContent(String content,String fromUserName){
        Matcher matcher = Pattern.compile("回复[ ]@[^@ ]{1,12}[ ]:").matcher(content);
        if(matcher.find() && matcher.start() == 0){
            String group = matcher.group();
            String userName = group.substring(4,group.length()-2);
            // 排除自己
            if(userName.equals(fromUserName)){
                return null;
            }
            //检查用户是否存在
            return noticeDao.findIdByUserName(userName);
        }else {
            return null;
        }
    }

    /**
     * 被@ 的用户id列表， 提取所有用户名之后，返回用户id列表
     * @param where 用于判断是帖子回复，还是楼层回复，当楼层回复的时候应去除回复前缀 回复[ ]@[^@ ]{1,12}[ ]:
     *              0表示帖子回复，1表示楼层回复
     */
    private List getAtIdFromContent(String content,String fromUserName,Integer where){
        if(where == 1){
            content = Pattern.compile("回复[ ]@[^@ ]{1,12}[ ]:").matcher(content).replaceFirst("");
        }
        Matcher matcher = Pattern.compile("@[^@ ]{1,12}[ ]").matcher(content);
        List<String> userList = new ArrayList();
        while (matcher.find()){
            String userName = matcher.group();
            userName = userName.substring(1,userName.length()-1);
            //排除自己
            if (!fromUserName.equals(userName)){
                userList.add(userName);
            }
        }
        if (userList.size() == 0){
            return null;
        }
        List<?> result = noticeDao.findIdByUserNameList(userList);
        return result;
    }
}
