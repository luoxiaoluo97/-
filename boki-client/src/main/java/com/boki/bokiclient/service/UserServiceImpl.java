package com.boki.bokiclient.service;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.UserDTO;
import com.boki.bokiapi.entity.dto.request.UserInfoDTO;
import com.boki.bokiapi.entity.po.FollowPO;
import com.boki.bokiapi.entity.po.PostCollectionPO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.*;
import com.boki.bokiclient.bean.DBSourceSelectBean;
import com.boki.bokiclient.dao.UserDao;
import com.boki.bokiclient.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/5
 * @Description:
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DBSourceSelectBean dbSource;

    @Override
    public UserInfoVO userInfo(Long userId) {
        UserDTO dto = userDao.findUserById(userId);
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(dto,vo);
        vo.setLevel(dbSource.getLv(vo.getExp()));
        vo.setHonorId(dbSource.getHonorId(vo.getCreditDegree()));
        vo.setRole(dbSource.getRoleById(vo.getRoleId()));
        return vo;
    }

    @Override
    public int modifyInfo(UserInfoDTO dto) {
        UserPO po = new UserPO();
        BeanUtils.copyProperties(dto,po);
        return userDao.updateUser(po);
    }

    @Override
    public int collectPost(Long postId, Long userId) {
        PostCollectionPO po = new PostCollectionPO();
        po.setPostId(postId);
        po.setUserId(userId);
        //是否重复收藏
        if (userDao.isPostCollectionExist(po) >= 1){
            return -1;
        }
        int count  = userDao.insertCollection(po);
        return count;
    }

    @Override
    public int removeCollection(Long postId, Long userId) {
        return userDao.removeCollection(postId,userId);
    }

    @Override
    public DataWithTotal findPostCollectionByUid(Long userId, Integer page) {
        List<List<?>> result = userDao.findPostCollectionByUid(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result,PostCollectionVO.class);
        return vo;
    }

    @Override
    public Integer addFollow(Long userId, Long toUserId) {
        FollowPO po = new FollowPO();
        po.setUserId(userId).setTargetUserId(toUserId);
        // 是否重复关注
        if(userDao.isFollowExist(po) >= 1){
            return -1;
        }
        return userDao.insertFollow(po);

    }

    @Override
    public Integer removeFollow(Long userId, Long toUserId) {
        FollowPO po = new FollowPO();
        po.setUserId(userId).setTargetUserId(toUserId);
        return userDao.deleteFollow(po);
    }

    @Override
    public DataWithTotal findFollowList(Long userId, Integer page) {
        List<List<?>> result = userDao.findFollowListByUID(userId,(page-1)*15,15);
        DataWithTotal vo  = new DataWithTotal();
        vo.input(result,FollowVO.class);
        return vo;
    }


    @Override
    public DataWithTotal findFans(Long userId, Integer page) {
        List<List<?>> result = userDao.findFans(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result,FollowVO.class);
        return vo;
    }

    @Override
    public DataWithTotal postHistory(Long userId, Integer page) {
        List<List<?>> result = userDao.findUserLastPosts(userId,null,(page-1)*15,15);
        for (Object dto : result.get(1)){
            PostDTO postDTO = ((PostDTO)dto);
            ((PostDTO)dto).setType(dbSource.getType(postDTO.getTypeId()));
        }
        DataWithTotal vo = new DataWithTotal();
        vo.input(result,PostHistoryVO.class);
        return vo;
    }

    @Override
    public DataWithTotal replyHistory(Long userId, Integer page){
        List<List<?>> result = userDao.findReplyHistory(userId,(page-1)*15,15);
        DataWithTotal vo = new DataWithTotal();
        vo.input(result,ReplyHistoryVO.class);
        return vo;
    }
}
