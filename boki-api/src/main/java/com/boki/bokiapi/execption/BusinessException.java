package com.boki.bokiapi.execption;

import com.boki.bokiapi.execption.enums.RequestResultCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Author: LJF
 * @Date: 2020/2/21
 * @Description:
 */

@Getter
@Setter
@Accessors(chain = true)
public class BusinessException extends RuntimeException {

    private RequestResultCode type;

    public BusinessException(){
        super();
    }

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, Throwable cause){
        super(message,cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
