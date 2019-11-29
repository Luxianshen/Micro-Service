package com.github.lujs.util;

import cn.hutool.json.JSONUtil;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.service.TransmitService;
import com.github.lujs.utils.RedisUtil;
import com.github.lujs.utils.SpringContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 初始化接口权限
 * @Author lujs
 * @Date 2019/11/27 14:51
 */
@Component
public class InitApiMapUtil {

    private final static TransmitService transmitService = SpringContextHolder.getBean(TransmitService.class);

    @PostConstruct
    private  static void initApiMap(){
        List<ApiEntity> apiList = transmitService.list();
        if (apiList!=null && apiList.size()>0) {
            Map<String, String> apiMap = apiList.stream().collect(Collectors.toMap(ApiEntity::getApiKey, ApiEntity::getPermissionCode,(k1, k2)->k1));
            RedisUtil.hashSet("apiMap",apiMap);
        }
    }

}