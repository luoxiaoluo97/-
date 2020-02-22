package com.boki.bokiapi.enums;

import com.boki.bokiapi.entity.vo.ExceptionResultVO;
import lombok.Getter;

/**
 * @Author: LJF
 * @Date: 2020/2/21
 * @Description: 请求状态码
 */
@Getter
public enum  RequestResultCode {

    /**
     * 数据格式错误
     */
    ERROR_MAIL(2001,"邮箱格式错误"),

    /**
     * 请求状态码
     */
    MAIL_SEND_FAIL(10100,"验证码发送失败。"),
    LOGIN_SUCCESS(10000,"登陆成功。"),
    LOGIN_FAIL(10001,"登陆失败，邮箱或密码错误。"),

    SERVER_ERROR(50000,"服务器错误");

    private Integer code;
    private String msg;

    RequestResultCode(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ExceptionResultVO getResult(){
        ExceptionResultVO result = new ExceptionResultVO();
        result.setCode(this.code)
                .setMsg(this.msg);
        return result;
    }
}
