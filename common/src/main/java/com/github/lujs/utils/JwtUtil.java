package com.github.lujs.utils;

import com.github.lujs.Exception.PermissionException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import io.jsonwebtoken.Jwts;

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

    public static Map<String,String> validateToken(String token){
        //判断token是否为空
        if(StringUtils.isNotEmpty(token)){
            //验证是否过期 todo
            HashMap<String,String> map = new HashMap<String,String>();
            //获取解析后的token body
            Map<String,Object> body = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            String id = String.valueOf(body.get("id"));
            String userName = (String)body.get("userName");
            if(StringUtils.isEmpty(userName)){
                throw new PermissionException("user is error, please check!");
            }
            map.put("id",id);
            map.put("user",userName);
            return map;
        }else {
            throw new PermissionException("token is null, please check!");
        }
    }

    /**
     * 根据用户name生成token
     * @param userName 用户名
     * @return
     */
    public static String generateToken(String userName) {

        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("id", new Random().nextInt());
        map.put("userName",userName);
        //生成jwt token
        String jwt = Jwts.builder().setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512,SECRET).compact();
        String finalJwt = TOKEN_PREFIX+" "+jwt;
        return finalJwt;
    }
}
