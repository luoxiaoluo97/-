package com.boki.bokiapi.execption.enums;

import com.boki.bokiapi.entity.vo.ResultVO;
import lombok.Getter;

/**
 * @Author: LJF
 * @Date: 2020/2/21
 * @Description: 请求状态码
 */
@Getter
public enum  RequestResultCode {

    /**
     * 数据错误
     */
    ERROR_MAIL(2001,"邮箱格式错误"),
    MAIL_ALREADY_EXIST(1000,"邮箱已被注册"),
    USERNAME_ALREADY_EXIST(1000,"昵称已被占用"),
    /**
     * 请求状态码 10xxx
     */
    LOGIN_SUCCESS(10000,"登陆成功。"),
    LOGIN_FAIL(10001,"登陆失败，邮箱或密码错误。"),
    MAIL_SEND_SUCCESS(10100,"验证码已发送。"),
    MAIL_SEND_FAIL(10101,"验证码发送失败。"),
    REGISTER_SUCCESS(10200,"注册完成。"),
    REGISTER_FAIL(10201,"注册失败。"),
    CHECK_CODE_INVALID(10301,"失效的验证码。"),
    CHECK_CODE_VALIDATION_FAILED(10302,"邮箱验证失败。"),

    /**
     * 服务器错误 500xx
     */
    SERVER_ERROR(50000,"服务器错误");

    private Integer code;
    private String msg;

    RequestResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultVO getResult(){
        ResultVO result = new ResultVO();
        result.setCode(this.code)
                .setMsg(this.msg);
        return result;
    }
}
