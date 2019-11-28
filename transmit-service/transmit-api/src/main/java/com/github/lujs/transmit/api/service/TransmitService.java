package com.github.lujs.transmit.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.transmit.api.model.ApiEntity;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
public interface TransmitService extends IService<ApiEntity> {

    /**
     * 获取接口（缓存）
     * @param apiKey apiKey
     * @return 接口信息
     */
    ApiEntity getApiByKey(String apiKey);
}
