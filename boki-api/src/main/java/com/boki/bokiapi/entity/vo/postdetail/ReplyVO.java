package com.boki.bokiapi.entity.vo.postdetail;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/29
 * @Description: 楼层信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ReplyVO {
    private Long id;                               //楼层id
    private Long floorNo;                          //第x楼
    private Long userId;                           //层主id
    private String userName;                       //层主昵称
    private Integer exp;                           //用户经验值
    private String creditDegree;                   //用户信用度
    private String role;                           //论坛角色
    private String content;                        //内容
//    private ArrayList<StoreyReplyVO> storeyReply;  //对该楼层的回复
//    private Integer count;                         //对该楼层的回复数
}
