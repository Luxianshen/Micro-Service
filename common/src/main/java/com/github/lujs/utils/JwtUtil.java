package com.github.lujs.utils;

import com.github.lujs.Exception.PermissionException;
import com.github.lujs.Exception.status.PermissionStatusCode;
import com.github.lujs.constant.CommonConstant;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Description: jwt生成工具类
 * @Author: lujs
 * @Data: 2019/4/2823:28
 * @version: 1.0.0
 */
@Component
public class JwtUtil {

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        JwtUtil.redisTemplate = redisTemplate;
    }

    public static Map<String, String> validateToken(String token, boolean flag) {
        //判断token是否为空
        if (StringUtils.isNotEmpty(token)) {
            HashMap<String, String> map = new HashMap<String, String>(2);
            String redisToken;
            //获取解析后的token body
            Map<String, Object> body;
            try {
                body = Jwts.parser().setSigningKey(CommonConstant.SECRET)
                        .parseClaimsJws(token.replace(CommonConstant.TOKEN_PREFIX, "")).getBody();
            } catch (Exception e) {
                throw new PermissionException(PermissionStatusCode.TOKEN_ILLEGAL);
            }
            String id = body.get(CommonConstant.HEADER_PARAM_ID).toString();
            String userName = body.get(CommonConstant.HEADER_PARAM_USER).toString();
            //从缓存获取
            if (flag) {
                redisToken = redisTemplate.opsForValue().get(CommonConstant.TOKEN_CODE + userName);
            } else {
                redisToken = redisTemplate.opsForValue().get(CommonConstant.API_TOKEN_CODE + userName);
            }
            //判断token是否合法
            if (StringUtils.isEmpty(userName)) {
                throw new PermissionException(PermissionStatusCode.TOKEN_ILLEGAL);
            } else if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                //验证是否过期和有效性
                throw new PermissionException(PermissionStatusCode.TOKEN_OVERDUE);
            }
            map.put(CommonConstant.HEADER_PARAM_ID, id);
            map.put(CommonConstant.HEADER_PARAM_USER, userName);
            //刷新缓存 默认续30分钟
            redisTemplate.expire(CommonConstant.TOKEN_CODE + userName, 3600L, TimeUnit.SECONDS);
            redisTemplate.expire(CommonConstant.TOKEN_CODE + userName + id, 3600L, TimeUnit.SECONDS);
            return map;
        } else {
            throw new PermissionException(PermissionStatusCode.NO_TOKEN);
        }
    }


    /**
     * 去除token
     *
     * @param token 用户token
     */
    public static void removeToken(String token) {
        //获取解析后的token body
        Map<String, Object> body = Jwts.parser().setSigningKey(CommonConstant.SECRET)
                .parseClaimsJws(token.replace(CommonConstant.TOKEN_PREFIX, "")).getBody();
        String id = String.valueOf(body.get(CommonConstant.HEADER_PARAM_ID));
        String userName = (String) body.get(CommonConstant.HEADER_PARAM_USER);
        JwtUtil.redisTemplate.delete(CommonConstant.TOKEN_CODE + userName);
        JwtUtil.redisTemplate.delete(CommonConstant.TOKEN_CODE + userName + id);
        JwtUtil.redisTemplate.delete(userName + "Menu");
    }

}
