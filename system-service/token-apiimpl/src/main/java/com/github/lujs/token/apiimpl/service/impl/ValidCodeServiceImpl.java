package com.github.lujs.token.apiimpl.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.github.lujs.token.apiimpl.service.ValidCodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ValidCodeServiceImpl implements ValidCodeService {

    private static final Logger logger = LoggerFactory.getLogger(ValidCodeServiceImpl.class);

    private static final String BASE64_IMAGE_PREFIX_PNG = "data:image/png;base64,";

    @Value("${application.validCode.timeout}")
    private Long timeout;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Boolean createValidCode(Integer width, Integer height) {
        try {
            //定义图形验证码的长、宽、验证码字符数、干扰元素个数
            ShearCaptcha circleCaptcha = CaptchaUtil.createShearCaptcha(width, height, 4, 4);
            //图形验证码写出，可以写出到文件，也可以写出到流
            String base64Code = BASE64_IMAGE_PREFIX_PNG + circleCaptcha.getImageBase64();
            String code = circleCaptcha.getCode();
            logger.debug("captchaId = {}, captchaText = {}", code, code);
            // 缓存验证码
            redisTemplate.opsForValue().set(code, code, timeout, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            logger.error("Captcha create failed: {}", e.getMessage(), e);
            return false;
            //throw new AdminCaptchaException(AdminCaptchaStatusCode.CAPTCHA_CREATE_ERROR);
        }
    }

    @Override
    public Boolean checkValidCode(String validCode) {
        if(StringUtils.isNotEmpty(validCode)){
            String code = (String) redisTemplate.opsForValue().get(validCode);
            if(StringUtils.isNotEmpty(code) && code.equals(validCode)){
                redisTemplate.delete(validCode);
                return true;
            }
        }
        return false;
    }
}
