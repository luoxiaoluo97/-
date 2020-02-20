//package com.boki.bokiclient.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//@Component
//public class BokiMvcConfig implements WebMvcConfigurer, HandlerInterceptor {
//
//    /*
//    HandlerInterceptor实现
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if(null != request.getSession().getAttribute("userName") ){
//            return true;
//        }
//        return false;
//    }
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//
//    /*
//    WebMvcConfigurer实现
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/static/**")
//                .excludePathPatterns("/login","/get/*");
//    }
//}
