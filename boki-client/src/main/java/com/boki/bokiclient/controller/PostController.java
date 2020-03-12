package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.PostDTO;
import com.boki.bokiapi.entity.dto.postdetail.ReplyDTO;
import com.boki.bokiapi.entity.dto.postdetail.StoreyReplyDTO;
import com.boki.bokiapi.entity.dto.request.PostSendDTO;
import com.boki.bokiapi.entity.dto.request.ReplySendDTO;
import com.boki.bokiapi.entity.dto.request.ReportSendDTO;
import com.boki.bokiapi.entity.dto.request.StoreyReplySendDTO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import com.boki.bokiclient.service.inter.NoticeService;
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

    @Autowired
    private NoticeService noticeService;

    /**
     * 发帖
     * @param dto
     * @param session
     * @return
     */
    @PostMapping("/sendPost")
    public ResultVO sendPost(@RequestBody @Valid PostSendDTO dto, HttpSession session){
        dto.setUserId( (Long)session.getAttribute("UID") );
        int i = postService.sendPost(dto);
        //给粉丝发送通知
        if(i >= 1){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(dto.getUserId())
                    .setFromUserName((String) session.getAttribute("userName"))
                    .setTitle(dto.getTitle());
            noticeService.sendNoticeToFans(NoticeMessage.TYPE_7, elem);
        }
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
        if ( i >= 1){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(dto.getUserId())
                    .setFromUserName((String) session.getAttribute("userName"))
                    .setContent(dto.getContent())
                    .setPostId(dto.getPostId());
            //给楼主通知
            noticeService.sendReplyNotice(NoticeMessage.TYPE_2, elem);
            //给被@用户通知，where为 0 表示层主进行@
            noticeService.sendNoticeByAt(NoticeMessage.TYPE_3,elem,0);

        }
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
        if ( i >= 1){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(dto.getUserId())
                    .setFromUserName((String) session.getAttribute("userName"))
                    .setContent(dto.getContent())
                    .setStoreyId(dto.getStoreyId());
            //给层主通知
            noticeService.sendStoreyReplyNotice(NoticeMessage.TYPE_2, elem);
            //给被回复者通知
            noticeService.sendNoticeWhenGotAReply(NoticeMessage.TYPE_2,elem);
            //给被@用户通知，where为 1 表示在楼中楼用户进行@
            noticeService.sendNoticeByAt(NoticeMessage.TYPE_3,elem,1);
        }
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

    /**
     * 举报帖子
     */
    @PostMapping("/report")
    public ResultVO reportPost(HttpSession session,@RequestBody @Valid ReportSendDTO dto){
        dto.setUserId((Long)session.getAttribute("UID"));
        int count = postService.reportPost(dto);
        return count >= 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 举报楼层
     */
    @PostMapping("/report/reply")
    public ResultVO reportReply(HttpSession session,@RequestBody @Valid ReportSendDTO dto){
        dto.setUserId((Long)session.getAttribute("UID"));
        int count = postService.reportReply(dto);
        return count >= 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 举报楼中楼
     */
    @PostMapping("/report/storyReply")
    public ResultVO reportStoryReply(HttpSession session,@RequestBody @Valid ReportSendDTO dto){
        dto.setUserId((Long)session.getAttribute("UID"));
        int count = postService.reportStoreyReply(dto);
        return count >= 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

}
