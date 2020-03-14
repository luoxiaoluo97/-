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
    NULL_MAIL(2000,"邮箱不能为空."),
    NULL_PWD(2000,"密码不能为空."),
    NULL_USERNAME(2000,"昵称不能为空."),
    NULL_CHECK_CODE(2000,"校验码不能为空."),
    NULL_SEX(2000,"性别不能为空."),
    NULL_TITLE(2000,"标题不能为空."),
    NULL_CONTENT(2000,"内容不能为空."),
    NULL_POST_ID(2000,"帖子id不能为空."),
    NULL_STOREY_ID(2000,"楼层id不能为空."),
    NULL_BIRTH(2000,"生日不能为空."),
    NULL_WHISPER_ID(2000,"私信id不能为空."),
    NULL_TARGET_USER(2000,"接收方为空."),
    NULL_TARGET_ID(2000,"目标id不能为空."),
    NULL_REASON(2000,"请填写举报路由."),
    NULL_PAGE(2000,"页数不能为空."),
    NULL_ROLE(2000,"必须赋予角色."),
    NULL_BAN_TIME(2000,"请输入禁封时长."),
    NULL_ONLY_DELETED(2000,"是否只看管理员删帖."),

    /**
     * 数据格式错误 2001
     */
    ERROR_MAIL(2001,"邮箱格式错误."),
    ERROR_PWD(2001,"密码为8~16位的数字+母字组成."),
    ERROR_USERNAME_FORMAT(2001,"昵称不能为空且不能有空格."),
    ERROR_USERNAME_SIZE(2001,"昵称1~12字."),
    ERROR_CHECK_CODE(2001,"校验码格式错误."),
    ERROR_SEX(2001,"错误的性别."),
    ERROR_IS_BANNED(2001,"禁封类型为n或y."),
    ERROR_MODE(2001,"错误了浏览方式."),
    ERROR_RANGE(2001,"错误的数据范围."),
    ERROR_ROLE(2001,"错误赋予角色."),

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
