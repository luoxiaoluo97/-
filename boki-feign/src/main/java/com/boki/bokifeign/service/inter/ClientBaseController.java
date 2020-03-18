package com.boki.bokifeign.service.inter;

import com.boki.bokifeign.entity.ResultVO;
import com.boki.bokifeign.service.ClientBaseControllerFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(value = "boki-client",fallbackFactory = ClientBaseControllerFallbackFactory.class)
public interface ClientBaseController {

    @GetMapping("/p/{postId}")
    ResultVO findPostById(@PathVariable("postId")Long id, @RequestParam("page") Integer page);
}
