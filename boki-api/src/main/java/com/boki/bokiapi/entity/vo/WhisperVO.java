package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description:
 */
@Data
@Accessors(chain = true)
public class WhisperVO {
    private Integer id;
    private Long firstUserId;           //私信方
    private String firstUserName;       //私信方昵称
    private String firstUserPhoto;      //私信方头像
    private Long secondUserId;          //接收方
    private String secondUserName;      //接收方昵称
    private String secondUserPhoto;     //接收方头像
    private ArrayList<WhisperDetailVO> list;
}
