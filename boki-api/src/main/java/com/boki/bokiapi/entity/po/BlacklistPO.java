package com.boki.bokiapi.entity.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description: 黑名单
 */
@Data
@Accessors(chain = true)
public class BlacklistPO {
    private Integer id;
    private Long userId;                //用户id
    private Long targetUserId;          //被拉黑用户id

    private String createTime;
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
