package com.boki.bokiclient.service.inter;

import com.alibaba.fastjson.JSONObject;
import com.boki.bokiapi.entity.dto.request.UserLoginDTO;
import com.boki.bokiapi.entity.dto.request.UserRegisterDTO;
import com.boki.bokiapi.entity.dto.request.UserUpdatePwdDTO;
import com.boki.bokiapi.entity.vo.UserInfoVO;

public interface LoginService {

    /**
     * 登陆验证
     * @param userLoginDTO
     * @return
     */
    UserInfoVO findByMailAndPwd(UserLoginDTO userLoginDTO);

    /**
     * 添加用户
     * @param userRegisterDTO
     * @return
     */
    int insertUser(UserRegisterDTO userRegisterDTO);

    /**
     * 发送验证码到指定邮箱，并缓存验证码
     * @param mail
     * @return 邮箱和验证码有效时间，单位：s
     */
    JSONObject sendCheckCodeAndCache(String mail);

    /**
     * 改密
     * @param userUpdatePwdDTO
     * @return
     */
    int updatePwdByMail(UserUpdatePwdDTO userUpdatePwdDTO);
}
