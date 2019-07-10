package com.github.lujs.token.apiimpl.service.impl;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.model.enums.LoginType;
import com.github.lujs.token.apiimpl.service.TokenService;
import com.github.lujs.token.apiimpl.service.ValidCodeService;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.utils.ToolSecurityPbkdf2;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Description: token服务实现类
 * @Author lujs
 * @Date 2019/7/10 13:50
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ValidCodeService validCodeService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${application.validCode}")
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
            user = phoneNoLogin(loginInfo);
        }
        if(user != null){
            //获取用户的角色列表 和 权限列表
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://localhost/get/{id}", Map.class, loginInfo.getUserName());
            Map<String,Object> userRoleAndPermission = responseEntity.getBody();
            UserInfo userInfo = new UserInfo();
            userInfo.setRoleList((List<String>)userRoleAndPermission.get("roleList"));
            userInfo.setPermissionList((List<String>)userRoleAndPermission.get("permissionList"));
            //生成token
            String random = String.valueOf(new Random().nextInt(6));
            String token = generateToken(random,user.getUsername());
            //放置在redis key 前缀+token随机数+用户名
            redisTemplate.opsForValue().set(CommonConstant.TOKEN_CODE+random+user.getUsername(),userInfo,5000L);

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
        //获取用户信息 todo 地址修改
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://localhost/get/{id}", User.class, loginInfo.getUserName());
        User user = responseEntity.getBody();
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
        map.put("userName", userName);

        //生成jwt token
        String jwt = Jwts.builder().setSubject("user info").setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        String finalJwt = TOKEN_PREFIX + " " + jwt;

        return finalJwt;
    }


}
