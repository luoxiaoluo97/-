package com.boki.bokiapi.entity.dto.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostDetailDTO {
    /**
     * 帖子属性，包括了1楼
     */
    private Long id;                            //帖子id
    private Long userId;                        //楼主id
    private String title;                       //标题
    private Long repliesCount;                  //回复数
    private Integer typeId;                     //帖子类型，精品贴或其他
}
