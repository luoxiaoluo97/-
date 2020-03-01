package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiclient.service.inter.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description: 公共页面,无需登陆即可访问
 */
@Slf4j
@Controller
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
    @GetMapping("/{id}")
    public ModelAndView findPostById(@PathVariable("id") Long id, ModelAndView mv){
        PostDetailVO post = commonService.getPostDetail(id);
        mv.addObject("postsContent",post);
        mv.setViewName("post");
        return mv;
    }

}
