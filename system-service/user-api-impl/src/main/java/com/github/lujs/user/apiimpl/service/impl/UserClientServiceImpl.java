package com.github.lujs.user.apiimpl.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.user.api.model.UserClient;
import com.github.lujs.user.api.service.UserClientService;
import com.github.lujs.user.apiimpl.mapper.UserClientMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户服务实现类
 * @Author lujs
 * @Date 2019/7/11 9:53
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserClientServiceImpl extends ServiceImpl<UserClientMapper, UserClient> implements UserClientService {


    @Override
    public UserClient getClientByAgentId(String agentId) {
        return baseMapper.getClientByAgentId(agentId);
    }

    @Override
    public boolean saveClient(UserClient userClient) {
        userClient.setAgentAuth(DigestUtils.md5Hex(RandomUtil.randomString(6)));
        userClient.setMacKey(DigestUtils.md5Hex(RandomUtil.randomString(5)));
        return save(userClient);
    }
}
