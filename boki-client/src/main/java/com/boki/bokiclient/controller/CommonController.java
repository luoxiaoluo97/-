package com.boki.bokiclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description: 公共页面
 */
@Slf4j
@Controller
@RequestMapping("/p")
public class CommonController {
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
}
