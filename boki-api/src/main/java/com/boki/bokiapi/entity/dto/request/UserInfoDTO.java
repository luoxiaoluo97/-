package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description:用户更改个人页面请求
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserInfoDTO {

    private Long id;

    @NotEmpty(message = StatusName.NULL_SEX )
    @Pattern(regexp = "男|女|保密",message = StatusName.ERROR_SEX)
    private String sex;             //用户性别

    @NotNull(message = StatusName.NULL_BIRTH )
    private String birth;           //出生日期

    private String comeFrom;            //来自何地
    private String intro;           //自我简介
    private String show;            //用户个性签名

}
