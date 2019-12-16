package com.github.lujs.transmit.apiimpl.util;

import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.service.TransmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/16 22:39
 */
@Component
@Slf4j
public class InitApiData implements ApplicationRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TransmitService transmitService;

    @Override
    @Async
    public void run(ApplicationArguments arg) throws Exception {
        // 加载接口权限缓存
        List<ApiEntity> apiList = transmitService.list();
        if (apiList != null && apiList.size() > 0) {
            log.info("开始初始化接口权限！");
            Map<String, String> apiMap = apiList.stream().collect(Collectors.toMap(ApiEntity::getApiKey, ApiEntity::getPermissionCode, (k1, k2) -> k1));
            redisTemplate.opsForHash().putAll("apiMap", apiMap);
            log.info("完成初始化接口权限！");
        }
    }
}

