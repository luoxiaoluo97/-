package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.WhisperVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import com.boki.bokiclient.service.inter.NoticeService;
import com.boki.bokiclient.service.inter.WhisperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author: LJF
 * @Date: 2020/3/7
 * @Description: 私信
 */
@Slf4j
@RestController
@RequestMapping("/whisper")
public class WhisperController {

    @Autowired
    private WhisperService whisperService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 打开私信，发起私信
     */
    @PostMapping("/open/{targetUserId}")
    public ResultVO open(@PathVariable Long targetUserId, HttpSession session){
        Long uId = (long)session.getAttribute("UID");
        if (uId == (long)targetUserId ){
            return RequestResultCode.WHISPER_CANNOT_SELF.getResult();
        }
        WhisperVO vo = whisperService.openWhisper(uId,targetUserId);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

    /**
     * 发送私信
     */
    @PostMapping("/send")
    public ResultVO send(@RequestBody @Valid WhisperSendDTO dto, HttpSession session){
        dto.setUserId((Long)session.getAttribute("UID"));
        if ((long)dto.getUserId() == (long)dto.getTargetUserId() ){
            return RequestResultCode.WHISPER_CANNOT_SELF.getResult();
        }
        int count = whisperService.sendWhisper(dto);
        //发送成功，通知对方
        if( count == 1) {
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(dto.getUserId())
                    .setFromUserName((String) session.getAttribute("userName"))
                    .setContent(dto.getContent())
                    .setWhisperId(dto.getWhisperId())
                    .setTargetUserId(dto.getTargetUserId());
            noticeService.sendWhisperNotice(NoticeMessage.TYPE_1, elem);
        }
        return count == 1 ? RequestResultCode.SUCCESS.getResult() :
                    count == -1 ? RequestResultCode.YOU_ARE_BLACKLISTED.getResult() :
                        RequestResultCode.FAIL.getResult();
    }


    /**
     * 私信列表
     */
    @GetMapping("/list/{page}")
    public ResultVO list(@PathVariable Integer page, HttpSession session){

        DataWithTotal vo = whisperService.getWhisperList((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

    /**
     * 移除私信
     */
    @PostMapping("/remove/{id}")
    public ResultVO remove(@PathVariable Integer id, HttpSession session){
        int count = whisperService.removeWhisper((Long)session.getAttribute("UID"),id);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 拉黑
     */
    @PostMapping("addBlacklist/{targetUserId}")
    public ResultVO addBlacklist(@PathVariable Long targetUserId, HttpSession session){
        Long uId = (long)session.getAttribute("UID");
        if (uId == (long)targetUserId ){
            return RequestResultCode.BLACKLIST_CANNOT_SELF.getResult();
        }
        int count = whisperService.addBlacklist((Long)session.getAttribute("UID"),targetUserId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.BLACKLIST_EXIST.getResult();
    }


    /**
     * 黑名单列表
     */
    @GetMapping("/blacklist/{page}")
    public ResultVO blacklist(@PathVariable Integer page, HttpSession session){
        DataWithTotal vo = whisperService.getBlacklist((Long)session.getAttribute("UID"),page);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }


    /**
     * 移除黑名单
     */
    @PostMapping("removeBlacklist/{blacklistId}")
    public ResultVO removeBlacklist(@PathVariable Integer blacklistId, HttpSession session){
        int count = whisperService.removeBlacklist((Long)session.getAttribute("UID"),blacklistId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }
}
