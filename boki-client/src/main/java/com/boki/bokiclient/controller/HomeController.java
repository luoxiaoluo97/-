package com.boki.bokiclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description:
 */

@Slf4j
@RestController
public class HomeController {

    /**
     * 论坛首页
     * @return
     */
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }
}
