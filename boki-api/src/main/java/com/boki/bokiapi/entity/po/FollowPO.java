package com.boki.bokiapi.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/6
 * @Description:
 */
@Data
@Accessors(chain = true)
public class FollowPO {

    private Integer id;
    private Long userId;            //粉丝
    private Long targetUserId;            //被关注者

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
