package com.boki.bokiapi.entity.dto;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Author: LJF
 * @Date: 2020/2/26
 * @Description: 帖子
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PostDTO {

    private Long id;                //帖子id
    private Long userId;            //楼主id
    private String userName;        //楼主昵称
    private Long creditDegree;      //楼主信用度

    @NotBlank(message = StatusName.NULL_TITLE)
    private String title;           //标题

    @NotBlank(message = StatusName.NULL_CONTENT)
    private String content;         //内容

    private String createTime;      //发帖时间
    private String lastReplier;     //最后回复者
    private String modifiedTime;    //最后回复时间
    private Long repliesCount;      //回复数
    private Integer typeId;         //类型
    private String isTop;           //是否置顶

}
