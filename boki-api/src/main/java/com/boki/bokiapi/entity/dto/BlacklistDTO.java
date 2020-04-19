package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Data
@Accessors(chain = true)
public class BlacklistDTO {
    private Integer id;
    private Long userId;                //昵称
    private String userName;            //昵称
    private String photo;               //头像
    private String intro;               //简介
    private String createTime;
}
