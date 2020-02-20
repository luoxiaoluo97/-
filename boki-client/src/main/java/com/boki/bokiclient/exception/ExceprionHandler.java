package com.boki.bokiclient.exception;

import com.boki.bokiapi.entity.vo.ExceptionResultVO;
import com.boki.bokiapi.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @time: 2020/2/16
 * @author: LJF
 * @description: 标识码和信息
 */
@Slf4j
@ControllerAdvice
public class ExceprionHandler {
//    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    /**
     * DTO数据校验
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResultVO handleValidException(MethodArgumentNotValidException e){
        String msg = e.getBindingResult().getFieldError().getDefaultMessage();
        return new ExceptionResultVO()
                .setCode(ResultCode.getCodeByMsg(msg))
                .setMsg(msg);
    }
}
