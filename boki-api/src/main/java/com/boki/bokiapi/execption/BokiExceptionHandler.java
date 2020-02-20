package com.boki.bokiapi.execption;

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
 * @description:
 */
@Slf4j
@ControllerAdvice
public class BokiExceptionHandler extends Exception {

    /**
     * 请求数据规范处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResultVO handleValidException(MethodArgumentNotValidException e){
        String type = e.getBindingResult().getFieldError().getDefaultMessage();
        ResultCode resultCode = ResultCode.valueOf(type);
        return new ExceptionResultVO()
                .setCode(resultCode.getCode())
                .setMsg(resultCode.getMsg());
    }

}
