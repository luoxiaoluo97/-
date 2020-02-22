package com.boki.bokiclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.boki.bokiapi.entity.dto.UserLoginDTO;
import com.boki.bokiapi.entity.dto.UserRegisterDTO;
import com.boki.bokiapi.entity.po.UserPO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.enums.RequestResultCode;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {


    @Autowired
    private LoginService loginService;


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


    @GetMapping(value = "/test")
    public ModelAndView test(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(new UserPO());
        modelAndView.setViewName("login/login");
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
            return RequestResultCode.LOGIN_FAIL.getResult();
        }
        session.setAttribute("userName",info.getUserName());
        return info;
    }

    /**
     * 用户注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping(value = "/register")
    public Object register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        loginService.insertUser(userRegisterDTO);
        return "index";
    }



    @GetMapping("/sendMailCode/{mail}")
    @ResponseBody
    public Object sendCheckCode(@PathVariable String mail){
        JSONObject result = loginService.sendCheckCodeAndCache(mail);
        return result;
    }

}
