package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author: LJF
 * @Date: 2020/2/27
 * @Description: 登陆后对帖子操作
 */
@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/sendPost")
    public ResultVO sendPost(@RequestBody @Valid PostDTO postDTO, HttpSession session){
        postDTO.setUserId( (Long)session.getAttribute("UID") );
        int i = postService.insertPost(postDTO);
        if(i == 1) {
            return RequestResultCode.SUCCESS.getResult();
        }else if( i == -1){
            return RequestResultCode.SHOULD_WAIT.getResult();
        }else {
            throw new BusinessException("发帖异常，帖子更新"+i+"条").setType(RequestResultCode.SERVER_ERROR);
        }
    }

    @PostMapping("/delete/{id}")
    public ResultVO deletePost(HttpSession session, @PathVariable("id") Long id){
        int count = postService.deletePost(new PostDTO()
                .setId(id)
                .setUserId( (Long)session.getAttribute("UID") ));
        if(count == 1) {
            return RequestResultCode.SUCCESS.getResult();
        }else {
            return RequestResultCode.POST_DELETE_FAIL.getResult();
        }
    }



}
