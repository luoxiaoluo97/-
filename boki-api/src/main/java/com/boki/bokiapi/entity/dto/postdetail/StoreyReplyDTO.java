package com.boki.bokiapi.entity.dto.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description: 对楼层回复，即楼中楼
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class StoreyReplyDTO {
    private Long id;                    //回复id
    private Long storeyId;              //所属楼层
    private Long userId;                //回复者id
    private String userName;            //回复者昵称
    private String createTime;          //回复时间
    private String content;             //回复内容
}
