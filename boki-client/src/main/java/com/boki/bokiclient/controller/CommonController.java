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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description: public公共页面,无需登陆即可访问
 */
@Slf4j
@RestController
@RequestMapping("/p")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    /**
     * 到注册页面
     * @param mv
     * @return
     */
    @GetMapping("/register")
    public ModelAndView registerPage(ModelAndView mv){
        mv.setViewName("register");
        return mv;
    }


    /**
     * 打开帖子
     * @param id
     * @return
     */
    @GetMapping("/{id}/{page}")
    public ResultVO findPostById(@PathVariable("id") Long id,@PathVariable("page") Integer page){
        PostDetailVO post = commonService.getPostDetail(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(post);
    }

    /**
     * 加载楼中楼
     */
    @GetMapping("/reply/{id}/{page}")
    public ResultVO findStoreyReply(@PathVariable("id") Long id,@PathVariable("page") Integer page){
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
    @GetMapping("/user/{userId}/lastPosts")
    public ResultVO userLastPosts(@PathVariable("userId")Long userId){
        DataWithTotal vo = commonService.getUserLastPosts(userId);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

}
