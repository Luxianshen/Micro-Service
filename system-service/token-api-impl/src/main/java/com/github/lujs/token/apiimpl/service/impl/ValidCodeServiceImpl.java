package com.github.lujs.token.apiimpl.service.impl;

import com.github.lujs.constant.CommonConstant;
import com.github.lujs.token.api.service.ValidCodeService;
import com.github.lujs.token.apiimpl.config.TokenProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description: 验证码服务实现类
 * @Author lujs
 * @Date 2019/7/10 14:20
 */
@Service
@AllArgsConstructor
@Slf4j
public class ValidCodeServiceImpl implements ValidCodeService {


    private final TokenProperties tokenProperties;

    private final RedisTemplate<String,String> redisTemplate;

    @Override
    public Boolean checkValidCode(String random, String validCode) {
        if (StringUtils.isNotEmpty(validCode)) {
            String code = redisTemplate.opsForValue().get(CommonConstant.SYS_CODE + random);
            if (StringUtils.isNotEmpty(code) && code.equals(validCode)) {
                redisTemplate.delete(CommonConstant.SYS_CODE + random);
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveImageCode(String random, String text) {
        //保存验证码
        redisTemplate.opsForValue().set(CommonConstant.SYS_CODE + random, text, tokenProperties.getCodeTime());
    }
}
