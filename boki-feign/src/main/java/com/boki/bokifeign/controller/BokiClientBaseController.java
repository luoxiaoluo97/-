package com.boki.bokifeign.controller;

import com.boki.bokifeign.entity.ResultVO;
import com.boki.bokifeign.service.inter.ClientBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BokiClientBaseController {

    @Autowired
    private ClientBaseController clientBaseController;

    @GetMapping("/p/{postId}")
    public ResultVO findPostById(@PathVariable("postId")Long id, Integer page){
        ResultVO vo = clientBaseController.findPostById(id,page);
        return vo;
    }

}
