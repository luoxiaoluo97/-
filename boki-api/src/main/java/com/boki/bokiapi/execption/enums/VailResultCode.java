package com.boki.bokiapi.execption.enums;

import com.boki.bokiapi.entity.vo.ResultVO;
import lombok.Getter;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description: 标识码和信息
 */
@Getter
public enum VailResultCode {

    /**
     * 空数据错误 2000
     */
    NULL_MAIL(2000,"邮箱不能为空"),
    NULL_PWD(2000,"密码不能为空"),
    NULL_USERNAME(2000,"昵称不能为空"),
    NULL_CHECK_CODE(2000,"校验码不能为空"),
    NULL_SEX(2000,"性别不能为空"),
    NULL_TITLE(2000,"标题不能为空"),
    NULL_CONTENT(2000,"内容不能为空"),
    NULL_POST_ID(2000,"帖子id不能为空"),
    NULL_STOREY_ID(2000,"楼层id不能为空"),
    NULL_BIRTH(2000,"生日不能为空"),

    /**
     * 数据格式错误 2001
     */
    ERROR_MAIL(2001,"邮箱格式错误"),
    ERROR_PWD(2001,"密码为8~16位的数字+母字组成"),
    ERROR_USERNAME_FORMAT(2001,"昵称不能为空且不能有空格"),
    ERROR_USERNAME_SIZE(2001,"昵称1~12字"),
    ERROR_CHECK_CODE(2001,"校验码格式错误"),
    ERROR_SEX(2001,"错误的性别"),

    /**
     * 日期错误 2003
     */
    ERROR_DATE(2003,"错误的日期"),
    ERROR_BIRTH(2003,"生日必须是过去的时间"),

    ;



    private Integer code;
    private String msg;
    VailResultCode(Integer code, String msg){
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
