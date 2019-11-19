package com.github.lujs.token.apiimpl.service.impl;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.auth.api.feign.AuthServiceClient;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.model.enums.LoginType;
import com.github.lujs.token.api.service.TokenService;
import com.github.lujs.token.api.service.ValidCodeService;
import com.github.lujs.user.api.feign.UserServiceClient;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.utils.RedisUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

/**
 * @Description: token服务实现类
 * @Author lujs
 * @Date 2019/7/10 13:50
 */
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Autowired
    private  ValidCodeService validCodeService;
    @Autowired
    private  UserServiceClient userServiceClient;
    @Autowired
    private  AuthServiceClient authServiceClient;

    @Value("${validCode.flag}")
    private Boolean validCodeRequired;

    private static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    public String login(LoginInfo loginInfo) {
        //开启了验证码
        if (validCodeRequired) {
            Boolean result = validCodeService.checkValidCode(loginInfo.getRandomStr(), loginInfo.getValidCode());
            if (!result) {
                throw new BaseException(GlobalStatusCode.INVALID_PARAMETER);
            }
        }
        User user = null;
        //正常执行登陆
        if (LoginType.NORMAL.code().equals(loginInfo.getLoginType())) {
            user = normalLogin(loginInfo);
        } else if (LoginType.PHONE_NO.code().equals(loginInfo.getLoginType())) {
            //todo
            user = phoneNoLogin(loginInfo);
        }
        if (user != null) {
            //获取用户的角色列表 和 权限列表
            UserInfo userInfo = new UserInfo();
            //获取用户角色列表
            BeanUtils.copyProperties(user, userInfo);
            userInfo.setRoleList(authServiceClient.getUserRoleList(user.getId()));
            userInfo.setPermissionList(authServiceClient.getRolePermissionList(userInfo.getRoleList()));
            //生成token
            String random = String.valueOf(new Random().nextInt(6));
            String token = generateToken(random, user.getAgentId());
            //放置在redis key 前缀+token随机数+用户名
            RedisUtil.set(CommonConstant.TOKEN_CODE + user.getAgentId(), token, 5000L);
            RedisUtil.set(CommonConstant.TOKEN_CODE + random + user.getAgentId(), userInfo, 5000L);

            return token.replace("Bearer ","");
        }
        return null;
    }

    /**
     * 用户一般登陆方法
     *
     * @param loginInfo
     * @return 用户信息
     */
    private User normalLogin(LoginInfo loginInfo) {
        //获取用户信息
        User user = userServiceClient.checkUserLoginInfo(loginInfo.getUserName(),loginInfo.getPassWord());
        if (user != null ) {
            return user;
        }else {
            throw new BaseException(GlobalStatusCode.OBJECT_IS_NOT_EXIST);
        }
    }

    /**
     * 电话验证码登陆
     *
     * @param loginInfo
     * @return
     */
    private User phoneNoLogin(LoginInfo loginInfo) {
        //todo
        return null;
    }

    /**
     * 生成token
     *
     * @param random   随机数
     * @param userName 用户名
     * @return token
     */
    private String generateToken(String random, String userName) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", random);
        map.put("user", userName);

        //生成jwt token
        String jwt = Jwts.builder().setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        String finalJwt = TOKEN_PREFIX + " " + jwt;

        return finalJwt;
    }


}