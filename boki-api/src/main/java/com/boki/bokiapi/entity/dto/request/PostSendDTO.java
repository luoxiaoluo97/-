package com.boki.bokiapi.entity.dto.request;

import com.boki.bokiapi.execption.StatusName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = StatusName.NULL_TITLE)
    private String title;           //标题

    @NotBlank(message = StatusName.NULL_CONTENT)
    private String content;         //内容
}
