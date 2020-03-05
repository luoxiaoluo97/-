package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description: 公共页面,无需登陆即可访问
 */
@Slf4j
@RestController
@RequestMapping("/p")
public class CommonController {

    @Autowired
    private CommonService commonService;

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
     * @param mv
     * @return
     */
    @GetMapping("/{id}/{page}")
    public ResultVO findPostById(@PathVariable("id") Long id,
                                     @PathVariable("page") Integer page,
                                     ModelAndView mv){
        PostDetailVO post = commonService.getPostDetail(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(post);
    }

    /**
     * 加载楼中楼
     */
    @GetMapping("/reply/{id}/{page}")
    public ResultVO findStoreyReply(@PathVariable("id") Long id,
                                    @PathVariable("page") Integer page){
        ArrayList<StoreyReplyVO> storeyReplies = commonService.findStoreyReplyById(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(storeyReplies);
    }

}
