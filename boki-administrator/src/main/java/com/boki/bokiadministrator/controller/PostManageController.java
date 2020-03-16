package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.NoticeService;
import com.boki.bokiadministrator.service.inter.PostManageService;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.postdetail.PostDetailVO;
import com.boki.bokiapi.entity.vo.postdetail.StoreyReplyVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @Author: LJF
 * @Date: 2020/3/13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/post")
public class PostManageController {

    @Autowired
    private PostManageService postManageService;

    @Autowired
    private NoticeService noticeService;


    /**
     * 帖子举报待审核列表
     */
    @GetMapping("/report/{type}/{page}")
    public ResultVO reportList(@PathVariable String type,@PathVariable Integer page){
        if(type == null || !type.equals("post") && !type.equals("reply") && !type.equals("storeyReply")){
            return RequestResultCode.REQUEST_ERROR.getResult();
        }
        DataWithTotal dwt = postManageService.getReportList(type,page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }



    /**
     * 驳回举报请求
     */
    @PostMapping("/report/reject")
    public ResultVO reject(@RequestBody @Valid ReportJudgeDTO dto){
        int count = postManageService.reportReject(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 举报成功，同意删帖
     */
    @PostMapping("/report/pass")
    public ResultVO pass(@RequestBody @Valid ReportJudgeDTO dto, HttpSession session){
        if(dto.getReason() == null || dto.getReason().isEmpty()){
            return RequestResultCode.NULL_REASON.getResult();
        }
        int count = postManageService.reportPass(dto);
        // 通知被删帖用户
        if (count > 0){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId((Long)session.getAttribute("UID"))
                    .setTargetUserId(dto.getUserId())
                    .setId(dto.getId())
                    .setReason(dto.getReason());
            if ("post".equals(dto.getType())) {
                noticeService.sendReportPassNotice(NoticeMessage.TYPE_4, elem);
            }else if ("reply".equals(dto.getType())){
                noticeService.sendReportPassNotice(NoticeMessage.TYPE_5,elem);
            }else if ("storeyReply".equals(dto.getType())){
                noticeService.sendReportPassNotice(NoticeMessage.TYPE_6, elem);
            }
        }
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 帖子列表
     * @return
     */
    @GetMapping("/list/{page}")
    public ResultVO index(@PathVariable Integer page){
        DataWithTotal dwt = postManageService.findPosts(page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 打开帖子
     * @param id
     * @return
     */
    @GetMapping("/{id}/{page}")
    public ResultVO findPostById(@PathVariable("id") Long id,@PathVariable("page") Integer page){
        PostDetailVO post = postManageService.getPostDetail(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(post);
    }

    /**
     * 加载楼中楼
     */
    @GetMapping("/reply/{id}/{page}")
    public ResultVO findStoreyReply(@PathVariable("id") Long id,@PathVariable("page") Integer page){
        ArrayList<StoreyReplyVO> storeyReplies = postManageService.findStoreyReplyById(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(storeyReplies);
    }



    /**
     * 加精
     */
//    @PostMapping("/upgrade")
    //TODO

    /**
     * 置顶
     */
//    @PostMapping("top")
    //TODO
}
