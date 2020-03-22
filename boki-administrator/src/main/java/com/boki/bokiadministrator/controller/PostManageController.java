package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.NoticeService;
import com.boki.bokiadministrator.service.inter.PostManageService;
import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
    @GetMapping("/report/{type}")
    public ResultVO reportList(@PathVariable String type,Integer page){
        page = page == null ? 1 : page <= 0 ? 1 : page;
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
    public ResultVO pass(@RequestBody @Valid ReportJudgeDTO dto, HttpServletRequest request){
        if(dto.getReason() == null || !Pattern.compile(".*[\\S]+.*").matcher(dto.getReason()).matches()){
            return RequestResultCode.NULL_REASON.getResult();
        }
        int count = postManageService.reportPass(dto);
        // 通知被删帖用户
        if (count > 0){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(Long.parseLong(request.getHeader("UID")))
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
     * @param type 1=所有帖子，2=只看精品
     * @return
     */
    @GetMapping("/list")
    public ResultVO postList(Integer type, Integer page){
        type = type == null ? 1 : type != 2 ? 1 : type;
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = postManageService.findPosts(type,page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

    /**
     * 打开帖子
     * @param id
     * @return
     */
    @GetMapping("/open")
    public ResultVO findPostById(Long id,Integer page){
        if ( id == null)return RequestResultCode.FAIL.getResult();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        PostDetailVO post = postManageService.getPostDetail(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(post);
    }

    /**
     * 加载楼中楼
     */
    @GetMapping("/reply/open")
    public ResultVO findStoreyReply(Long id,Integer page){
        if ( id == null)return RequestResultCode.FAIL.getResult();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        ArrayList<StoreyReplyVO> storeyReplies = postManageService.findStoreyReplyById(id,page);
        return RequestResultCode.SUCCESS.getResult().setData(storeyReplies);
    }



    /**
     * 加精或降级
     */
    @PostMapping("/upgrade")
    public ResultVO postUpgrade(@RequestBody @Valid PostUpgradeDTO dto,HttpServletRequest request){
        dto.setUId( Long.parseLong(request.getHeader("UID")) );
        int count = postManageService.postUpgrade(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 置顶
     */
    @PostMapping("/setTop")
    public ResultVO postSetTop(@RequestBody @Valid PostSetTopDTO dto, HttpServletRequest request){
        dto.setUId( Long.parseLong(request.getHeader("UID")));
        int count = postManageService.postSetTop(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 取消置顶
     */
    @PostMapping("/cancelTop/{postId}")
    public ResultVO cancelTop(@PathVariable Long postId , HttpServletRequest request){
        int count = postManageService.postCancelTop(postId,Long.parseLong(request.getHeader("UID")));
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

}
