package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.dto.request.WhisperSendDTO;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.entity.vo.WhisperVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
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
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
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
        return RequestResultCode.SUCCESS.getResult();
    }


    /**
     * 拉黑
     */
    @PostMapping("addBlacklist/{userId}")
    public ResultVO addBlacklist(@PathVariable Integer userId, HttpSession session){
        // TODO
        return RequestResultCode.SUCCESS.getResult();
    }

    /**
     * 黑名单列表
     */
    @GetMapping("/blacklist/{page}")
    public ResultVO blacklist(@PathVariable Integer page, HttpSession session){
        // TODO
        return RequestResultCode.SUCCESS.getResult();
    }


    /**
     * 移除黑名单
     */
    @PostMapping("removeBlacklist/{userId}")
    public ResultVO removeBlacklist(@PathVariable Integer userId, HttpSession session){
        // TODO
        return RequestResultCode.SUCCESS.getResult();
    }
}
