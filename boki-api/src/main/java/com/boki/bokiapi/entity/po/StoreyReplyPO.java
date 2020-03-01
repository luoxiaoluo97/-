package com.boki.bokiapi.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 二级回复，即楼层的回复
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreyReplyPO {

    private Long id;                    //回复id
    private Long storeyId;              //所属楼层
    private Long userId;                //回复者id
    private Long toUserId;              //对xx回复
    private String content;             //回复内容

    private String createTime;          //回复时间
    private String modifiedTime;
    private String creator;
    private String modifier;
    private String isDeleted;
}
