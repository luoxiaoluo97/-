package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.dto.request.PostSendDTO;
import com.boki.bokiapi.entity.dto.request.ReplySendDTO;
import com.boki.bokiapi.entity.dto.request.StoreyReplySendDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
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

    /**
     * 发帖
     * @param postSendDTO
     * @param session
     * @return
     */
    @PostMapping("/sendPost")
    public ResultVO sendPost(@RequestBody @Valid PostSendDTO postSendDTO, HttpSession session){
        postSendDTO.setUserId( (Long)session.getAttribute("UID") );
        int i = postService.sendPost(postSendDTO);
        return i >= 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.SHOULD_WAIT.getResult();
    }


    /**
     * 回帖
     * @param dto ReplySendDTO
     * @param session
     * @return
     */
    @PostMapping("/sendReply")
    public ResultVO sendReply(@RequestBody @Valid ReplySendDTO dto, HttpSession session){
        dto.setUserId( (Long) session.getAttribute("UID"));
        int i = postService.sendReply(dto);
        return i == -1 ? RequestResultCode.SHOULD_WAIT.getResult() : RequestResultCode.SUCCESS.getResult();
    }

    /**
     * 回复层主
     * @param dto
     * @param session
     * @return
     */
    @PostMapping("/sendStoreyReply")
    public ResultVO sendStoreyReply(@RequestBody @Valid StoreyReplySendDTO dto, HttpSession session){
        dto.setUserId( (Long) session.getAttribute("UID"));
        int i = postService.sendStoreyReply(dto);
        return i == -1 ? RequestResultCode.SHOULD_WAIT.getResult() : RequestResultCode.SUCCESS.getResult();
    }


    /**
     * 删帖
     * @param session
     * @param id
     * @return
     */
    @PostMapping("/deletePost/{id}")
    public ResultVO deletePost(HttpSession session, @PathVariable("id") Long id){
        // FIXME dao层需要修改，删帖时应该连同楼层和楼中楼一起删除
        int count = postService.deletePost(new PostDTO()
                .setId(id)
                .setUserId( (Long)session.getAttribute("UID") ));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.POST_DELETE_FAIL.getResult();
    }

    /**
     * 删楼
     */
    @PostMapping("/deleteReply/{id}")
    public ResultVO deleteReply(HttpSession session, @PathVariable("id") Long id){
        int count = postService.deleteReply(new ReplyDTO()
            .setId(id)
            .setUserId( (Long)session.getAttribute("UID") ));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.POST_DELETE_FAIL.getResult();
    }

    /**
     * 删除楼层回复，即删除楼中楼
     */
    @PostMapping("deleteStoreyReply/{id}")
    public ResultVO deleteStoreyReply(HttpSession session, @PathVariable("id") Long id){
        int count = postService.deleteStoreyReply(new StoreyReplyDTO()
                .setId(id)
                .setUserId( (Long)session.getAttribute("UID") ));
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.POST_DELETE_FAIL.getResult();
    }
    // TODO 删楼中楼
}
