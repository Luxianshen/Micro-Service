package com.github.lujs.token.apiimpl.service.impl;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.auth.apiimpl.service.RoleMenuService;
import com.github.lujs.auth.apiimpl.service.UserRoleService;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.model.enums.LoginType;
import com.github.lujs.token.apiimpl.service.TokenService;
import com.github.lujs.token.apiimpl.service.ValidCodeService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.userapiimpl.service.UserService;
import com.github.lujs.utils.RedisUtil;
import com.github.lujs.utils.ToolSecurityPbkdf2;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Random;

/**
 * @Description: token服务实现类
 * @Author lujs
 * @Date 2019/7/10 13:50
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private ValidCodeService validCodeService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleMenuService roleMenuService;

    @Value("${validCode.flag}")
    private Boolean validCodeRequired;

    public static final String SECRET = "qazwsx123444$#%#()*&& asdaswwi1235 ?;!@#kmmmpom in***xx**&";
    public static final String TOKEN_PREFIX = "Bearer";

    @Override
    public String login(LoginInfo loginInfo) {
        //开启了验证码
        if (validCodeRequired) {
            Boolean result = validCodeService.checkValidCode(loginInfo.getValidCode());
            if (!result) {
                throw new BaseException(GlobalStatusCode.INVALID_PARAMETER); //todo
            }
        }
        User user = null;
        //正常执行登陆
        if (LoginType.NORMAL.code().equals(loginInfo.getLoginType())) {
            user = normalLogin(loginInfo);
        } else if (LoginType.PHONE_NO.code().equals(loginInfo.getLoginType())) {
            user = phoneNoLogin(loginInfo);  //todo
        }
        if(user != null){
            //获取用户的角色列表 和 权限列表

            UserInfo userInfo = new UserInfo();
            //获取用户角色列表
            BeanUtils.copyProperties(user, userInfo);
            userInfo.setRoleList(userRoleService.getUserRoleList(user.getUsername()));
            userInfo.setPermissionList(roleMenuService.getUserPermissionList(user.getUsername()));
            //生成token
            String random = String.valueOf(new Random().nextInt(6));
            String token = generateToken(random,user.getUsername());
            //放置在redis key 前缀+token随机数+用户名
            RedisUtil.set(CommonConstant.TOKEN_CODE+user.getUsername(),token,5000L);
            RedisUtil.set(CommonConstant.TOKEN_CODE+random+user.getUsername(),userInfo,5000L);

            return token;
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
        User user = userService.getUserInfoByName(loginInfo.getUserName());
        if(user != null && user.getStatus().equals(CommonConstant.STATUS_NORMAL)){
            //匹配密码
            boolean success = false;
            try {
                success = ToolSecurityPbkdf2.authenticate(loginInfo.getPassWord(), user.getPassword(), user.getSalt());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
            if (success) {
                return user;
            } else {
                throw new BaseException(GlobalStatusCode.OBJECT_IS_NOT_EXIST); //todo
            }
        }
        return null;
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

    public String generateToken(String random,String userName) {
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
