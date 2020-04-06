package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.security.Token;
import com.boki.bokiadministrator.service.inter.BokiDataService;
import com.boki.bokiapi.entity.vo.SummaryVO;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LJF
 * @Date: 2020/3/13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/statistics")
public class BokiDataController {

    @Autowired
    private BokiDataService bokiDataService;

    /**
     * 数据总览：用户数，帖子数，管理员删帖数，用户人均发帖，近一个月日均发帖量，
     */
    @Token
    @GetMapping("")
    public ResultVO statistics(){
        SummaryVO vo = bokiDataService.statistics();
        return RequestResultCode.SUCCESS.getResult().setData(vo);
    }
}
