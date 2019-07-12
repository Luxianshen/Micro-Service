package com.github.lujs.token.apiimpl.service;

/**
 * @Description: 验证码服务
 * @Author lujs
 * @Date 2019/7/10 14:20
 */
public interface ValidCodeService {

    /**
     * 验证 验证码
     * @return 创建结果
     */
    public Boolean checkValidCode(String random,String validCode);

    /**
     * 保存 验证码
     * @return 创建结果
     */
    void saveImageCode(String random, String text);
}
