package com.boki.bokiadministrator.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.boki.bokiadministrator.security.Token;
import com.boki.bokiapi.execption.BusinessException;
import com.boki.bokiapi.execption.enums.RequestResultCode;
import com.boki.bokiapi.value.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LJF
 * @Date: 2020/3/21
 * @Description:
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        Method method = ( (HandlerMethod)handler ).getMethod();
        if (!method.isAnnotationPresent(Token.class)) {
            return true;
        }
        String token = request.getHeader(Common.TOKEN);
        if (token == null) {
            throw new BusinessException().setType(RequestResultCode.LOGIN_TODO);
        }
        // 从缓存中获取 tokenId 的值，并验证
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
            Map tokenCache = redisTemplate.opsForHash().entries(Common.TOKEN + userId);
            if (tokenCache.size() == 0){
                throw new BusinessException().setType(RequestResultCode.LOGIN_TODO);
            }
            if ( "1".equals(tokenCache.get("roleId").toString()) ){
                throw new BusinessException().setType(RequestResultCode.NO_AUTHORIZATION);
            }
            String time = tokenCache.get("time").toString();
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256( time )).build();
            jwtVerifier.verify(token);
            redisTemplate.expire(Common.TOKEN + userId, 1800, TimeUnit.SECONDS);
        } catch (JWTDecodeException j) {
            throw new BusinessException().setType(RequestResultCode.LOGIN_TODO);
        } catch (JWTVerificationException e) {
            throw new BusinessException().setType(RequestResultCode.LOGIN_TODO);
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
