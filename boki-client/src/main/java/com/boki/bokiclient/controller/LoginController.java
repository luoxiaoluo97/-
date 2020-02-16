package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.po.User;
import com.boki.bokiapi.util.MailCheck;
import com.boki.bokiclient.service.LoginService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MailCheck mailCheck;

    @Value("${spring.mail.username}")
    private String username;

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "login/login";
    }


    @GetMapping(value = "/get/{id}")
    public ModelAndView test(@PathVariable int id ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new User());
        modelAndView.setViewName("login/login.html");
        mailCheck.mailSend("");
        return modelAndView;
    }


    @PostMapping(value = "/login")
    public String login(User user,
                            HttpSession session){
        session.setAttribute("userName",user.getUserName());
        return "index";
    }

    @PostMapping(value = "/register")
    public String register(HttpServletRequest request,
                           HttpSession session,
                           @RequestBody User user){
        session.setAttribute("userName",user.getUserName());
        return "index";
    }



    @Test
    public void test(){

    }
}
