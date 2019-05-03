package com.github.lujs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: service基类
 * @Author: lujs
 * @Data: 2019/5/3 15:17
 * @version: 1.0.0
 */
public abstract class BaseService {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());
}