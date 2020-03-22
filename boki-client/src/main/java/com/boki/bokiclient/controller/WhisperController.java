package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.WhisperVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.notice.NoticeElem;
import com.boki.bokiapi.value.notice.NoticeMessage;
import com.boki.bokiclient.service.NoticeServiceImpl;
import com.boki.bokiclient.service.inter.NoticeService;
import com.boki.bokiclient.service.inter.WhisperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResultVO open(@PathVariable Long targetUserId, HttpServletRequest request){
        Long uId = Long.parseLong(request.getHeader("UID"));
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
    public ResultVO send(@RequestBody @Valid WhisperSendDTO dto, HttpServletRequest request){
        dto.setUserId(Long.parseLong(request.getHeader("UID")));
        String userName = NoticeServiceImpl.decode(request.getHeader("userName"),dto.getUserId());
        if (dto.getUserId().longValue() == dto.getTargetUserId().longValue() ){
            return RequestResultCode.WHISPER_CANNOT_SELF.getResult();
        }
        int count = whisperService.sendWhisper(dto);
        //发送成功，通知对方
        if( count == 1) {
            NoticeElem elem = new NoticeElem()
                    .setFromUserId(dto.getUserId())
                    .setFromUserName(userName)
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
    @GetMapping("/list")
    public ResultVO list(Integer page, HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal vo = whisperService.getWhisperList(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }

    /**
     * 从列表中移除私信
     */
    @PostMapping("/remove/{id}")
    public ResultVO remove(@PathVariable Integer id, HttpServletRequest request){
        int count = whisperService.removeWhisper(Long.parseLong(request.getHeader("UID")),id);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 拉黑
     */
    @PostMapping("addBlacklist/{targetUserId}")
    public ResultVO addBlacklist(@PathVariable Long targetUserId, HttpServletRequest request){
        Long uId = Long.parseLong(request.getHeader("UID"));
        if (uId == targetUserId.longValue() ){
            return RequestResultCode.BLACKLIST_CANNOT_SELF.getResult();
        }
        int count = whisperService.addBlacklist(uId,targetUserId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.BLACKLIST_EXIST.getResult();
    }


    /**
     * 黑名单列表
     */
    @GetMapping("/blacklist")
    public ResultVO blacklist(Integer page, HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal vo = whisperService.getBlacklist(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }


    /**
     * 移除黑名单
     */
    @PostMapping("removeBlacklist/{blacklistId}")
    public ResultVO removeBlacklist(@PathVariable Integer blacklistId, HttpServletRequest request){
        int count = whisperService.removeBlacklist(Long.parseLong(request.getHeader("UID")),blacklistId);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }
}
