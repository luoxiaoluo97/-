package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/9
 * @Description:
 */
@Data
@Accessors(chain = true)
public class BlacklistVO {
    private Integer id;
    private Long userId;                //昵称
    private String userName;            //昵称
    private String photo;               //头像

}
