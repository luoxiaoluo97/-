package com.boki.bokiadministrator.controller;

import com.boki.bokiadministrator.service.inter.ImageSupportService;
import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: LJF
 * @Date: 2020/3/12
 * @Description:
 */

@Slf4j
@RestController
@RequestMapping("/images")
public class ImageSupportController {

    @Autowired
    private ImageSupportService imageSupportService;


    /**
     * 上传支持，返回图片url
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResultVO uploadFile(@RequestParam("fileName") MultipartFile file) {
        String  url = imageSupportService.saveImage(file);
        return RequestResultCode.SUCCESS.getResult().setData(url);
    }



}
