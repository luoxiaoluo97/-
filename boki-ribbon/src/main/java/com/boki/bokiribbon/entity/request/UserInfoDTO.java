package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    private String sex;             //用户性别

    private String birth;           //出生日期

    private String from;            //来自何地
    private String intro;           //自我简介
    private String show;            //用户个性签名

}
