package com.github.lujs.userapiimpl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.user.api.model.User;
import com.github.lujs.user.api.service.UserService;
import com.github.lujs.userapiimpl.mapper.UserMapper;
import com.github.lujs.utils.SysUtils;
import com.github.lujs.utils.ToolSecurityPbkdf2;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description: 用户服务实现类
 * @Author lujs
 * @Date 2019/7/11 9:53
 */
@AllArgsConstructor
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public User get(User user) {

        return null;
    }

    /**
     * 获取用户信息方法
     *
     * @param agentId 用户名
     * @return 用户对象
     */
    @Override
    public User checkUserLoginInfo(String agentId, String agentAuth) {
        User user = userMapper.getUserByAgentId(agentId);
        if (user != null) {
            //匹配密码
            String credential = "";
            try {
                // 开始解密
                credential = SysUtils.decryptAES(agentAuth, "1234567887654321");
                credential = credential.trim();
                log.debug("credential decrypt success:{}", credential);
            } catch (Exception e) {
                log.error("credential decrypt fail:{}", credential);
            }
            try {
                ToolSecurityPbkdf2.authenticate(credential, user.getAgentAuth(), user.getSalt());
                return user;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
