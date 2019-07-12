package com.github.lujs.token.apiimpl.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.token.apiimpl.service.ValidCodeService;
import com.github.lujs.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 验证码服务实现类
 * @Author lujs
 * @Date 2019/7/10 14:20
 */
@Service
@Slf4j
public class ValidCodeServiceImpl implements ValidCodeService {

    @Value("${validCode.timeout}")
    private Long timeout;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Boolean checkValidCode(String random,String validCode) {
        if(StringUtils.isNotEmpty(validCode)){
            String code = (String) RedisUtil.get(CommonConstant.SYS_CODE+random);
            if(StringUtils.isNotEmpty(code) && code.equals(validCode)){
                redisTemplate.delete(CommonConstant.SYS_CODE+random);
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveImageCode(String random, String text) {
        //保存验证码
        RedisUtil.set(CommonConstant.SYS_CODE+random, text, timeout);
    }
}
