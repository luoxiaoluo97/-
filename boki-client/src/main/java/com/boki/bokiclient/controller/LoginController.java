package com.boki.bokiclient.controller;

import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public Object test(@PathVariable int id){
        return id+1;
    }

    @Test
    public void test(){

    }
}
