package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: LJF
 * @Date: 2020/3/3
 * @Description:
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ReplySendDTO {

    private Long userId;            //层主id
    private Long floorNo = 0L;      //第x楼

    @NotNull(message = StatusName.NULL_POST_ID)
    private Long postId;            //所属帖子

    @NotNull(message = StatusName.NULL_CONTENT)
    private String content;         //回复内容

    @NotBlank(message = StatusName.NULL_CONTENT)
    private String shortContent;         //缩略内容，无html
}
