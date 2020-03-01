package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.HomeVO;
import com.boki.bokiclient.service.inter.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description:
 */

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 论坛首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(HttpSession session){
        if(session.getAttribute("UID") != null) {
            // TODO 访问首页 如果已经登陆，还应该显示动态、私信、和其他可能的消息
        }
        HomeVO homeVO = homeService.findPosts(1);
        ModelAndView mv = new ModelAndView();
        mv.addObject("postsList", homeVO);
        mv.setViewName("index");
        return mv;
    }
}
