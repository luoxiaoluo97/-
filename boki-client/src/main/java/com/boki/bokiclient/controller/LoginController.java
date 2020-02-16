package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.util.MailCheck;
import com.boki.bokiclient.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MailCheck mailCheck;

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new UserPO());
        return "login/login";
    }


    @GetMapping(value = "/get/{id}")
    public ModelAndView test(@PathVariable int id ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new UserPO());
        modelAndView.setViewName("login/login.html");
        mailCheck.mailSend("");
        return modelAndView;
    }


    @PostMapping(value = "/login")
    public String login(UserPO user,
                        HttpSession session){
        session.setAttribute("userName",user.getUserName());
        return "index";
    }

    @PostMapping(value = "/register")
    public String register(HttpServletRequest request,
                           HttpSession session,
                           @RequestBody UserPO user){
        session.setAttribute("userName",user.getUserName());
        return "index";
    }



    @Test
    public void test(){

    }
}
