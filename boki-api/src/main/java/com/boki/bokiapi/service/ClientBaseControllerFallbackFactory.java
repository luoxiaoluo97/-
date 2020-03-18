package com.boki.bokiapi.service;

import com.boki.bokiapi.service.inter.ClientBaseController;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientBaseControllerFallbackFactory implements FallbackFactory<ClientBaseController> {

    @Override
    public ClientBaseController create(Throwable throwable) {
        return new ClientBaseController() {
            @Override
            public Object test(int id) {
                System.out.println("这里");
                return "莫得1号啊";
            }
        };
    }
}
