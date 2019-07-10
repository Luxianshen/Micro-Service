package com.github.lujs.utils;

import com.github.lujs.Exception.PermissionException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.jsonwebtoken.Jwts;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @Description: jwt生成工具类
 * @Author: lujs
 * @Data: 2019/4/2823:28
 * @version: 1.0.0
 */
public class JwtUtil {

    private static RedisTemplate redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "Authorization";

    public static Map<String,String> validateToken(String token){
        //判断token是否为空
        if(StringUtils.isNotEmpty(token)){
            HashMap<String,String> map = new HashMap<String,String>();
            //获取解析后的token body
            Map<String,Object> body = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            String id = String.valueOf(body.get("id"));
            String userName = (String)body.get("userName");
            //从缓存获取
            String redisToken = (String) redisTemplate.opsForValue().get("Token:"+userName);
            //判断token是否合法
            if(StringUtils.isEmpty(userName)){
                throw new PermissionException("user is error, please check!");
            }else if(StringUtils.isEmpty(redisToken) || !redisToken.equals(token)){
                //验证是否过期和有效性
                throw new PermissionException("token is overdue, please check!");
            }
            map.put("id",id);
            map.put("user",userName);
            return map;
        }else {
            throw new PermissionException("token is null, please check!");
        }
    }


}
