package com.boki.bokiadministrator.controller;

import com.auth0.jwt.JWT;
import com.boki.bokiadministrator.security.Token;
import com.boki.bokiadministrator.service.inter.NoticeService;
import com.boki.bokiadministrator.service.inter.PostManageService;
import com.boki.bokiapi.entity.dto.request.PostSetTopDTO;
import com.boki.bokiapi.entity.dto.request.PostUpgradeDTO;
import com.boki.bokiapi.entity.dto.request.ReportJudgeDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
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
    @Token
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
    @Token
    @PostMapping("/report/reject")
    public ResultVO reject(@RequestBody @Valid ReportJudgeDTO dto){
        int count = postManageService.reportReject(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 举报成功，同意删帖
     */
    @Token
    @PostMapping("/report/pass")
    public ResultVO pass(@RequestBody @Valid ReportJudgeDTO dto, HttpServletRequest request){
        if(dto.getReason() == null || !Pattern.compile(".*[\\S]+.*").matcher(dto.getReason()).matches()){
            return RequestResultCode.NULL_REASON.getResult();
        }
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = postManageService.reportPass(dto);
        // 通知被删帖用户
        if (count > 0){
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(Long.parseLong(audience.get(0)))
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
     * 加精或降级
     */
    @Token
    @PostMapping("/upgrade")
    public ResultVO postUpgrade(@RequestBody @Valid PostUpgradeDTO dto,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        dto.setUId( Long.parseLong(audience.get(0)) );
        int count = postManageService.postUpgrade(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 置顶
     */
    @Token
    @PostMapping("/setTop")
    public ResultVO postSetTop(@RequestBody @Valid PostSetTopDTO dto, HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        dto.setUId( Long.parseLong(audience.get(0)));
        int count = postManageService.postSetTop(dto);
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

    /**
     * 取消置顶
     */
    @Token
    @PostMapping("/cancelTop/{postId}")
    public ResultVO cancelTop(@PathVariable Long postId , HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = postManageService.postCancelTop( postId, Long.parseLong(audience.get(0)) );
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }

}
