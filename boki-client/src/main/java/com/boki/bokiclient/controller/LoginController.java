package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.User;
import com.boki.bokiclient.service.LoginService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new User());
        return "login/login";
    }


    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ModelAndView test(@PathVariable int id ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new User());
        modelAndView.setViewName("login/login.html");
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
