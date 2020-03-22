package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.LoginService;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.util.PwdEncryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @PostMapping(value = "")
    public ResultVO login(@Valid @RequestBody UserLoginDTO user){
        user.setPwd(PwdEncryption.doubleMd5(user.getPwd()));
        UserInfoVO info = loginService.findByMailAndPwd(user);
        if (info != null && 1 == info.getRoleId().intValue()){
            throw new BusinessException("水友"+user.getMail()+"试图登陆管理端.").setType(RequestResultCode.NO_AUTHORIZATION);
        }
        return info != null ? RequestResultCode.SUCCESS.getResult().setData(info) :
                RequestResultCode.LOGIN_FAIL.getResult();
    }


}
