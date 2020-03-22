package com.boki.bokiapi.execption;

import com.boki.bokiapi.entity.vo.ResultVO;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.execption.enums.VailResultCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.ExecutorException;
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
    public ResultVO handleValidException(MethodArgumentNotValidException e){
        String type = e.getBindingResult().getFieldError().getDefaultMessage();
        VailResultCode vailResultCode = VailResultCode.valueOf(type);
        log.info(vailResultCode.getCode()+vailResultCode.getMsg());
        return vailResultCode.getResult();
    }

    /**
     * 自定义异常，事务异常处理
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResultVO handleValException(BusinessException e){
        if (e.getMessage() != null) {
            log.warn("原因:" + e.getMessage());
        }
        if ( e.getType() != null ) {
            log.info(e.getType().getCode()+e.getType().getMsg());
            return e.getType().getResult().setData(e.getInfo());
        }else {
            log.info(RequestResultCode.SERVER_ERROR.getCode()+RequestResultCode.SERVER_ERROR.getMsg());
            return RequestResultCode.SERVER_ERROR.getResult();
        }
    }

    /**
     * 错误的接收数据
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ExecutorException.class)
    public ResultVO handleValException(ExecutorException e){
        log.warn("SQL错误，原因为:"+e.getMessage());
        return RequestResultCode.SQL_ERROR.getResult();
    }

}
