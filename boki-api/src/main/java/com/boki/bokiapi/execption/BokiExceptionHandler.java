package com.boki.bokiapi.execption;

import com.boki.bokiapi.entity.vo.ExceptionResultVO;
import com.boki.bokiapi.enums.RequestResultCode;
import com.boki.bokiapi.enums.VailResultCode;
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
public class BokiExceptionHandler{

    /**
     * 请求数据规范处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResultVO handleValidException(MethodArgumentNotValidException e){
        String type = e.getBindingResult().getFieldError().getDefaultMessage();
        VailResultCode Result = VailResultCode.valueOf(type);
        log.info(Result.getCode()+Result.getMsg());
        return new ExceptionResultVO()
                .setCode(Result.getCode())
                .setMsg(Result.getMsg());
    }

    /**
     * 事务异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ExceptionResultVO handleValException(BusinessException e){
        log.warn(e.getMessage());
        ExceptionResultVO resultVO = new ExceptionResultVO();
        if ( e.getType() != null) {
            RequestResultCode requestResultCode = RequestResultCode.valueOf(e.getType());
            return resultVO.setCode(requestResultCode.getCode())
                    .setMsg(requestResultCode.getMsg());
        }else {
            return resultVO.setCode(RequestResultCode.SERVER_ERROR.getCode())
                    .setMsg(RequestResultCode.SERVER_ERROR.getMsg());
        }
    }

}
