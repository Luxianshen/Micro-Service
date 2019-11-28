package com.github.lujs.transmit.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.transmit.apiimpl.mapper.TransmitMapper;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.service.TransmitService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
@Service
public class TransmitServiceImpl extends ServiceImpl<TransmitMapper, ApiEntity> implements TransmitService {

    @Override
    @Cacheable(value = "transmit" , key = "#apiKey", unless = "#result == null ")
    public ApiEntity getApiByKey(String apiKey) {
        QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_key", apiKey);
        return getOne(queryWrapper);
    }
}
