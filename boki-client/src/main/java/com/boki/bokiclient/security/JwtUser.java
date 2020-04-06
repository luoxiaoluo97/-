package com.boki.bokiclient.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.boki.bokiapi.entity.vo.UserInfoVO;
import com.boki.bokiapi.value.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LJF
 * @Date: 2020/4/1
 * @Description:
 */

@Component
public class JwtUser {

    @Autowired
    private RedisTemplate redisTemplate;

    public  String getToken(UserInfoVO vo) {
        String time = new Date().getTime() + Common.EMPTY;  //时间作为验证
        String token= JWT.create().withAudience(
                vo.getId().toString(),
                vo.getMail(),
                vo.getUserName())
//                .withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
                .sign(Algorithm.HMAC256( time ));
        Map tokenCache = new HashMap();
        tokenCache.put("time",time);
        tokenCache.put("roleId",vo.getRoleId().toString());
        redisTemplate.opsForHash().putAll(Common.TOKEN + vo.getId(),tokenCache);
        redisTemplate.expire(Common.TOKEN + vo.getId(), 1800, TimeUnit.SECONDS);
        return token;

    }

//    public Boolean
}

