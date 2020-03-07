package com.boki.bokiapi.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/6
 * @Description:
 */
@Data
@Accessors(chain = true)
public class FollowDTO {

    private Long targetUserId;            //被关注者
    private String targetUserName;        //被关注者昵称
    private String photo;                 //头像
    private String intro;                 //自我介绍
}
