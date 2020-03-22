package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.CommonService;
import com.boki.bokiclient.service.inter.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description: public公共页面,无需登陆即可访问
 */
@Slf4j
@RestController
@RequestMapping(value = "/p",produces = {"application/json;charset=UTF-8"})
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;


    /**
     * 打开帖子
     * @param id
     * @return
     */
    @GetMapping(value = "/{postId}")
    public ResultVO findPostById(@PathVariable("postId") Long id, Integer page){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        PostDetailVO post = commonService.getPostDetail(id,page);
        return post != null ? RequestResultCode.SUCCESS.getResult().setData(post)
                : RequestResultCode.POST_NOTFOUND.getResult();
    }

    /**
     * 加载楼中楼
     */
    @GetMapping("/reply/open")
    public ResultVO findStoreyReply( Long id,Integer page){
        if ( id == null)return RequestResultCode.FAIL.getResult();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        ArrayList<StoreyReplyVO> storeyReplies = commonService.findStoreyReplyById(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(storeyReplies);
    }


    /**
     * 查看他人信息，此为开放性接口
     */
    @GetMapping("/UID{id}")
    public ResultVO userInfo(@PathVariable("id") Long id){
        UserInfoVO vo = userService.userInfo(id);
        return vo != null ? RequestResultCode.SUCCESS.getResult().setData(vo) : RequestResultCode.FAIL.getResult();
    }

    /**
     * 用户近三天发帖情况
     */
    @GetMapping("/user/lastPosts/UID{userId}")
    public ResultVO userLastPosts(@PathVariable("userId")Long userId){
        DataWithTotal vo = commonService.getUserLastPosts(userId);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

}
