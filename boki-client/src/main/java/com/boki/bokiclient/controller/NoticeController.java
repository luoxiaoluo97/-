package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @GetMapping("/count")
    public ResultVO noticeCount(HttpServletRequest request){
        Integer count = noticeService.getNoticeCount(Long.parseLong(request.getHeader("UID")));
        return RequestResultCode.SUCCESS.getResult().setData(count);
    }


    /**
     * 通知列表
     */
    @GetMapping("/list")
    public ResultVO noticeList(Integer page, HttpServletRequest request){
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = noticeService.getNoticeList(Long.parseLong(request.getHeader("UID")),page);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }


    /**
     * 移除单个通知
     */
    @PostMapping("/remove/{id}")
    public ResultVO removeNotice(@PathVariable Integer id,HttpServletRequest request){
        int count = noticeService.removeNotice(Long.parseLong(request.getHeader("UID")),id);
        return count == 1 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }


    /**
     * 清空通知列表
     */
    @PostMapping("/clear")
    public ResultVO clearNotice(HttpServletRequest request){
        int count = noticeService.clearNotice(Long.parseLong(request.getHeader("UID")));
        return count > 0 ? RequestResultCode.SUCCESS.getResult() : RequestResultCode.FAIL.getResult();
    }
}
