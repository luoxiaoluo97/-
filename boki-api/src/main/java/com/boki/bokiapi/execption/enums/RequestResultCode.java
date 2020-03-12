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
    ERROR_DATE(2001,"错误的日期"),
    MAIL_ALREADY_EXIST(1000,"邮箱已被注册"),
    USERNAME_ALREADY_EXIST(1000,"昵称已被占用"),
    ERROR_FLOOR(1001,"错误的楼层回复：1楼"),
    /**
     * 请求状态码 10xxx
     */
    FAIL(9999,"失败"),
    SUCCESS(10000,"成功"),
    LOGIN_FAIL(10001,"登陆失败，邮箱或密码错误。"),
    LOGIN_TODO(10002,"登陆后可操作"),
    MAIL_SEND_FAIL(10101,"验证码发送失败。"),
    REGISTER_FAIL(10201,"注册失败。"),
    CHECK_CODE_INVALID(10301,"失效的验证码。"),
    CHECK_CODE_VALIDATION_FAILED(10302,"邮箱验证失败。"),

    SHOULD_WAIT(10401,"防灌水，15秒后可继续发帖"),
    POST_DELETE_FAIL(10501,"删贴失败"),
    BIRTH_IN_THE_PATH(10601,"生日必须是过去"),
    COLLECTION_EXIST(10700,"已存在于收藏列表"),

    FOLLOW_EXIST(10800,"已存在于关注列表"),
    FOLLOW_CANNOT_SELF(10801,"无法关注自己"),
    WHISPER_CANNOT_SELF(10901,"无法私信自己"),
    YOU_ARE_BLACKLISTED(10902,"你被对方拉黑了"),

    BLACKLIST_CANNOT_SELF(11001,"无法拉黑自己"),
    BLACKLIST_EXIST(11002,"已存在于黑名单"),


    /**
     * 服务器错误 500xx
     */
    SERVER_ERROR(50000,"服务器错误"),
    SQL_ERROR(50001,"错误的数据");

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
