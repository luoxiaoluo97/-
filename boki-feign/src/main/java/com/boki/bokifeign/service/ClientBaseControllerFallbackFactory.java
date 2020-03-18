package com.boki.bokifeign.service;


import com.boki.bokifeign.entity.RequestResultCode;
import com.boki.bokifeign.entity.ResultVO;
import com.boki.bokifeign.service.inter.ClientBaseController;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientBaseControllerFallbackFactory implements FallbackFactory<ClientBaseController> {

    @Override
    public ClientBaseController create(Throwable throwable) {
        return new ClientBaseController() {

            @Override
            public ResultVO findPostById(Long id, Integer page) {
                return RequestResultCode.SERVER_ERROR.getResult();
            }
        };
    }
}
