package com.boki.bokiapi.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class HomeVO {
    ArrayList<PostVO> posts;                    //帖子列表
    private Long postsCount;                     //帖子总量
}
