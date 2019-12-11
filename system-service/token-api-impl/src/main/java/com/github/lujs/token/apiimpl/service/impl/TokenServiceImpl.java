package com.github.lujs.token.apiimpl.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.github.lujs.Exception.BaseException;
import com.github.lujs.auth.api.feign.AuthServiceClient;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.model.enums.LoginType;
import com.github.lujs.token.api.service.TokenService;
import com.github.lujs.token.api.service.ValidCodeService;
import com.github.lujs.transmit.api.service.feign.TransmitServiceClient;
import com.github.lujs.user.api.feign.UserServiceClient;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserClient;
import com.github.lujs.user.api.model.UserClientInfo;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.utils.JwtUtil;
import com.github.lujs.utils.RedisUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private TransmitServiceClient transmitServiceClient;

    @Value("${validCode.flag}")
    private Boolean validCodeRequired;

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
            RoleQuery roleQuery = new RoleQuery();
            roleQuery.setAgentId(user.getAgentId());
            roleQuery.setRoles(userInfo.getRoleList());
            userInfo.setPermissionList(authServiceClient.getRolePermissionList(roleQuery));
            //userInfo.setApiList(transmitServiceClient.getRoleApiList(roleQuery)); todo 暂时不用
            //生成token
            String random = RandomUtil.randomString(5);
            String token = generateToken(random, user.getAgentId());
            //清除旧token
            RedisUtil.delete(CommonConstant.TOKEN_CODE + user.getAgentId());
            //放置在redis key 前缀+token随机数+用户名
            RedisUtil.set(CommonConstant.TOKEN_CODE + user.getAgentId(), token, 5000L);
            RedisUtil.set(CommonConstant.TOKEN_CODE + user.getAgentId() + random, userInfo, 5000L);

            return token;
        }
        return null;
    }

    /**
     * 生成客户端token
     * @param userClient 客户端
     * @return token
     */
    @Override
    public String generateClientToken(UserClient userClient) {

        //获取客户端接口权限
        UserClientInfo userClientInfo = new UserClientInfo();
        userClientInfo.setAgentId(userClient.getAgentId());
        List<String> clientApiList = transmitServiceClient.getClientApiList(userClient.getId());
        userClientInfo.setApiList(clientApiList);
        //生成token
        String random = RandomUtil.randomString(5);
        String token = generateToken(random, userClient.getAgentId());
        //清除旧token
        RedisUtil.delete(CommonConstant.API_TOKEN_CODE + userClient.getAgentId());
        //放置在redis key 前缀+token随机数+用户名
        RedisUtil.set(CommonConstant.API_TOKEN_CODE + userClient.getAgentId(), token, 5000L);
        RedisUtil.set(CommonConstant.API_TOKEN_CODE + userClient.getAgentId() + random, userClientInfo, 5000L);

        return token;
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
                .signWith(SignatureAlgorithm.HS512, JwtUtil.SECRET).compact();
        return JwtUtil.TOKEN_PREFIX + " " + jwt;
    }


}
