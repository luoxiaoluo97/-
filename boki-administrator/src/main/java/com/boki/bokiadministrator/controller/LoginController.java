package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.LoginService;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.util.PwdEncryption;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginService loginService;


    /**
     * 登陆请求
     * @param user
     * @param session
     * @return
     */
    @PostMapping(value = "")
    public ResultVO login(@Valid @RequestBody UserLoginDTO user,
                          HttpSession session){
        user.setPwd(PwdEncryption.doubleMd5(user.getPwd()));
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getMail(),user.getPwd());
        UserInfoVO info;
        try{
            subject.login(token);
            info = (UserInfoVO)subject.getPrincipal();
        }catch (UnknownAccountException e){
            throw new BusinessException(e.getMessage()).setType(RequestResultCode.LOGIN_FAIL);
        }
        session.setAttribute("UID",info.getId());
        session.setAttribute("mail",info.getMail());
        session.setAttribute("userName",info.getUserName());
        return RequestResultCode.SUCCESS.getResult().setData(info);
    }


}
