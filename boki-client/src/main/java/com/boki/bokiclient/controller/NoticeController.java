package com.boki.bokiclient.controller;

import com.auth0.jwt.JWT;
import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
import com.boki.bokiclient.security.Token;
import com.boki.bokiclient.service.inter.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: LJF
 * @Date: 2020/3/12
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 获取通知数量
     */
    @Token
    @GetMapping("/count")
    public ResultVO noticeCount(HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        Integer count = noticeService.getNoticeCount(Long.parseLong(audience.get(0)));
        return RequestResultCode.SUCCESS.getResult().setData(count);
    }


    /**
     * 通知列表
     */
    @Token
    @GetMapping("/list")
    public ResultVO noticeList(Integer page, HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = noticeService.getNoticeList(Long.parseLong(audience.get(0)),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


    /**
     * 移除单个通知
     */
    @Token
    @PostMapping("/remove/{id}")
    public ResultVO removeNotice(@PathVariable Integer id,HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = noticeService.removeNotice(Long.parseLong(audience.get(0)),id);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 清空通知列表
     */
    @Token
    @PostMapping("/clear")
    public ResultVO clearNotice(HttpServletRequest request){
        List<String> audience= JWT.decode(request.getHeader(Common.TOKEN)).getAudience();
        int count = noticeService.clearNotice(Long.parseLong(audience.get(0)));
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }
}
