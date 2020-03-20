package com.boki.bokiclient.config;

import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: LJF
 * @Date: 2020/3/20
 * @Description:
 */
public class ClientInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("执行了TestInterceptor的preHandle方法");
        if(request.getHeader("UID") != null && request.getHeader("cookie") != null){
            return true;
        }
        throw new BusinessException().setType(RequestResultCode.LOGIN_TODO);
//        response.sendRedirect(request.getContextPath()+"你的登陆页地址");
//        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
