package com.boki.bokifeign.controller;

import com.boki.bokiapi.service.inter.ClientBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientBaseControllerService {

    @Autowired
    private ClientBaseController clientBaseController;

    @RequestMapping(value = "/feign/get/{id}",method = RequestMethod.GET)
    public Object test(@PathVariable("id") int id){
        return clientBaseController.test(id);
    }

}
