package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description:
 */

@Slf4j
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * 论坛首页
     * @param session
     * @return
     */
    @GetMapping("/")
    public ModelAndView home(HttpSession session){
        if(session.getAttribute("UID") != null) {
            // TODO 访问首页 如果已经登陆，还应该显示动态、私信、和其他可能的消息
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    /**
     * 帖子列表
     * @return
     */
    @GetMapping("/home/{page}")
    public ResultVO index(@PathVariable Integer page){
        DataWithTotal dwt = homeService.findPosts(page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

}
