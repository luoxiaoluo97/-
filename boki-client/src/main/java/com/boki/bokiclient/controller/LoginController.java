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
import com.boki.bokiapi.value.Common;
import com.boki.bokiclient.security.JwtUser;
import com.boki.bokiclient.security.Token;
import com.boki.bokiclient.service.inter.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping(value = "/login",produces = {"application/json;charset=UTF-8"})
public class LoginController {


    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUser jwtUser;

    /**
     * 登陆请求
     * @param user
     * @return
     */
    @PostMapping(value = "")
    public ResultVO login(@Valid @RequestBody UserLoginDTO user){
        user.setPwd(PwdEncryption.doubleMd5(user.getPwd()));
        UserInfoVO info = loginService.findByMailAndPwd(user);
        if( info == null ){
            return RequestResultCode.LOGIN_FAIL.getResult();
        }
        info.setToken(jwtUser.getToken(info));
        return RequestResultCode.SUCCESS.getResult().setData(info);
    }

    /**
     * 仅用于判断当前token是否有效，通过拦截就判定为有效
     */
    @Token
    @GetMapping("/judge")
    public ResultVO judge(){
        return RequestResultCode.SUCCESS.getResult();
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
        Pattern pattern = Pattern.compile(Common.PATTERN_MAIL);
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
    public ResultVO updatePwd(@RequestBody @Valid UserUpdatePwdDTO dto){
        int count = loginService.updatePwdByMail(dto);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.SERVER_ERROR.getResult();
    }

}
