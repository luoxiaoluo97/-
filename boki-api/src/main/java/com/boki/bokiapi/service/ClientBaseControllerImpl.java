package com.boki.bokiapi.service;

import feign.hystrix.FallbackFactory;

public class ClientBaseControllerImpl implements FallbackFactory<ClientBaseController> {
    public ClientBaseController create(Throwable throwable) {
        return null;
    }
}
