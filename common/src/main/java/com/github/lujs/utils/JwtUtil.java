package com.github.lujs.utils;

import com.github.lujs.Exception.PermissionException;
import com.github.lujs.Exception.status.PermissionStatusCode;
import com.github.lujs.constant.CommonConstant;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * @Description: jwt生成工具类
 * @Author: lujs
 * @Data: 2019/4/2823:28
 * @version: 1.0.0
 */
public class JwtUtil {

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_AUTH = "Authorization";
    public static final String API_REQ = "apiKey";

    public static Map<String, String> validateToken(String token,boolean flag) {
        //判断token是否为空
        if (StringUtils.isNotEmpty(token)) {
            HashMap<String, String> map = new HashMap<String, String>();
            //获取解析后的token body
            Map<String, Object> body;
            try{
                body = Jwts.parser().setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            }catch (Exception e){
                throw new PermissionException(PermissionStatusCode.TOKEN_ILLEGAL);
            }
            String id = body.get("id").toString();
            String userName = body.get("user").toString();
            //从缓存获取
            String redisToken;
            if(flag){
                redisToken = (String) RedisUtil.get(CommonConstant.TOKEN_CODE + userName);
            }else {
                redisToken = (String) RedisUtil.get(CommonConstant.API_TOKEN_CODE + userName);
            }
            //判断token是否合法
            if (StringUtils.isEmpty(userName)) {
                throw new PermissionException(PermissionStatusCode.TOKEN_ILLEGAL);
            } else if (StringUtils.isEmpty(redisToken) || !redisToken.equals(token)) {
                //验证是否过期和有效性
                throw new PermissionException(PermissionStatusCode.TOKEN_OVERDUE);
            }
            map.put("id", id);
            map.put("user", userName);
            //刷新缓存
            //RedisUtil.setExpire(CommonConstant.TOKEN_CODE + userName,5000L);
            //RedisUtil.setExpire(CommonConstant.TOKEN_CODE + userName + id, 5000L);
            return map;
        } else {
            throw new PermissionException(PermissionStatusCode.NO_TOKEN);
        }
    }


    /**
     * 去除token
     * @param token 用户token
     */
    public static void removeToken(String token) {
        //获取解析后的token body
        Map<String, Object> body = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
        String id = String.valueOf(body.get("id"));
        String userName = (String) body.get("user");
        RedisUtil.delete(CommonConstant.TOKEN_CODE + userName);
        RedisUtil.delete(CommonConstant.TOKEN_CODE + userName + id);
        RedisUtil.delete(userName+"Menu");
    }


}
