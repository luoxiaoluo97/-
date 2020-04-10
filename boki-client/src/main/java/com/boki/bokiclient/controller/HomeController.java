package com.boki.bokiclient.controller;

import com.boki.bokiapi.entity.vo.DataWithTotal;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiclient.service.inter.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LJF
 * @Date: 2020/2/22
 * @Description:
 */

@Slf4j
@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;


    /**
     * 帖子列表
     * @param type 1=所有帖子，2=只看精品
     * @return
     */
    @GetMapping("/")
    public ResultVO index(Integer type, Integer page,String titleKey){
        type = type == null ? 1 : type != 2 ? 1 : type;
        page = page == null ? 1 : page <= 0 ? 1 : page;
        DataWithTotal dwt = homeService.findPosts(type,page,titleKey);
        return RequestResultCode.SUCCESS.getResult().setData(dwt);
    }

}
