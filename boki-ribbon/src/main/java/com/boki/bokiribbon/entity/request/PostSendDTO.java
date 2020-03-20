package com.boki.bokiribbon.entity.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/3/3
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostSendDTO {

    private Long userId;            //楼主id

    private String title;           //标题

    private String content;         //内容
}
