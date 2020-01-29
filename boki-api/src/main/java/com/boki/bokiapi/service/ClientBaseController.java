package com.boki.bokiapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "boki-client")
public interface ClientBaseController {

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    Object test(@PathVariable("id") int id);

}
