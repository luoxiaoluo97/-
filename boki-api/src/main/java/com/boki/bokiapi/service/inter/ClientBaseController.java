package com.boki.bokiapi.service.inter;

import com.boki.bokiapi.service.ClientBaseControllerFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "boki-client",fallbackFactory = ClientBaseControllerFallbackFactory.class)
public interface ClientBaseController {

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    Object test(@PathVariable("id") int id);

}
