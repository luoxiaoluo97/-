package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.enums.ResultCode;
import com.boki.bokiapi.util.MailCheck;
import com.boki.bokiapi.util.PwdEncryption;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private MailCheck mailCheck;

    @GetMapping("/")
    public String main(Model model){
        model.addAttribute("user",new UserPO());
        return "login/login";
    }

    @GetMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("user",new UserPO());
        return "login/login";
    }


    @GetMapping(value = "/get/{id}")
    public ModelAndView test(@PathVariable int id ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new UserPO());
        modelAndView.setViewName("login/login");
        mailCheck.mailSend("");
        return modelAndView;
    }

    /**
     * 登陆请求
     * @param user
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Object login(@Valid @RequestBody UserLoginDTO user,
                        HttpSession session){
        user.setPwd(PwdEncryption.doubleMd5(user.getPwd()));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getMail(),user.getPwd());
        UserInfoVO info;
        try{
            subject.login(token);
            info = (UserInfoVO)subject.getPrincipal();
        }catch (UnknownAccountException e){
            log.info("用户邮箱或密码错误。");
            return ResultCode.LOGIN_FAIL.getResult();
        }
        session.setAttribute("userName",info.getUserName());
        return info;
    }

    @PostMapping(value = "/register")
    public String register(HttpServletRequest request,
                           HttpSession session,
                           @RequestBody UserPO user){
        session.setAttribute("userName",user.getUserName());
        return "index";
    }

}
