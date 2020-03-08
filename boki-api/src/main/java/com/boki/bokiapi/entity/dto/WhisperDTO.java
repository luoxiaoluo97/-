package com.boki.bokiapi.entity.dto;

import com.boki.bokiapi.entity.po.WhisperDetailPO;
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
public class WhisperDTO {
    private Integer id;
    private Long firstUserId;           //甲方
    private String firstUserName;       //甲方昵称
    private String firstUserPhoto;      //甲方头像
    private Long secondUserId;          //乙方
    private String secondUserName;      //乙方昵称
    private String secondUserPhoto;     //乙方头像
    private ArrayList<WhisperDetailPO> list;
}
