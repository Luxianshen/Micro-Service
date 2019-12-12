package com.github.lujs.transmit.apiimpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.service.TransmitService;
import com.github.lujs.transmit.apiimpl.mapper.TransmitMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/11/26 11:49
 */
@AllArgsConstructor
@Service
public class TransmitServiceImpl extends ServiceImpl<TransmitMapper, ApiEntity> implements TransmitService {

    private final RedisTemplate redisTemplate;

    @Override
    @Cacheable(value = "transmits" , key = "#apiKey")
    public ApiEntity getApiByKey(String apiKey) {
        QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_key", apiKey);
        return getOne(queryWrapper);
    }

    @Override
    @CacheEvict(value = "transmits" , key = "#apiEntity.apiKey" )
    public Boolean updateApi(ApiEntity apiEntity) {
        redisTemplate.opsForHash().put("apiMap", apiEntity.getApiKey(), apiEntity.getPermissionCode());
        return updateById(apiEntity);
    }

    @Override
    @CacheEvict(value = "transmits" , key = "#apiEntity.apiKey")
    public Boolean deleteById(ApiEntity apiEntity) {
        //去除权限缓存 和接口缓存
        redisTemplate.opsForHash().delete("apiMap", apiEntity.getApiKey());
        return removeById(apiEntity.getId());
    }

}
