package com.boki.bokiclient.controller;

import com.alibaba.fastjson.JSONObject;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.dto.request.UserRegisterDTO;
import com.boki.bokiapi.entity.dto.request.UserUpdatePwdDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.util.PwdEncryption;
import com.boki.bokiapi.value.CommonString;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {


    @Autowired
    private LoginService loginService;


    /**
     * 登陆请求
     * @param user
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
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

    /**
     * 用户注册
     * @param userRegisterDTO
     * @return
     */
    @PostMapping(value = "/register")
    public ResultVO register(@Valid @RequestBody UserRegisterDTO userRegisterDTO){
        loginService.insertUser(userRegisterDTO);
        log.info(userRegisterDTO.getMail()+" 用户完成注册。");
        return RequestResultCode.SUCCESS.getResult();
    }


    /**
     * 邮箱验证码发送请求
     * @param mail
     * @return
     */
    @GetMapping("/sendCheckCode/{mail}")
    public ResultVO sendCheckCode(@PathVariable String mail){
        Pattern pattern = Pattern.compile(CommonString.PATTERN_MAIL);
        if (!pattern.matcher(mail).matches()){
            throw new BusinessException().setType(RequestResultCode.ERROR_MAIL);
        }
        JSONObject result = loginService.sendCheckCodeAndCache(mail);
        return RequestResultCode.SUCCESS.getResult().setData(result);
    }


    /**
     * 改密请求
     * @return
     */
    @PostMapping("/modifyPwd")
    public ResultVO updatePwd(@RequestBody @Valid UserUpdatePwdDTO userUpdatePwdDTO, HttpSession session){
        String mail = (String)session.getAttribute("mail");
        userUpdatePwdDTO.setMail(mail);
        int count = loginService.updatePwdByMail(userUpdatePwdDTO);
        return count == 1 || count == 0 ?
                RequestResultCode.SUCCESS.getResult() : RequestResultCode.SERVER_ERROR.getResult();
    }
}
