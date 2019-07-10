package com.github.lujs.token.apiimpl.service;

/**
 * @Description: 验证码服务
 * @Author lujs
 * @Date 2019/7/10 14:20
 */
public interface ValidCodeService {

    /**
     * 创建 验证码
     * @return 创建结果
     */
    public Boolean createValidCode(Integer width, Integer height);

    /**
     * 验证 验证码
     * @return 创建结果
     */
    public Boolean checkValidCode(String validCode);
}
