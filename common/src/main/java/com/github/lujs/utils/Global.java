package com.github.lujs.utils;

import com.github.lujs.common.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Describe: 获取配置文件属性
 * @Author: lujs
 * @Date: 2019/4/28 16:05
 * @Version: 1.0.0
 **/
public class Global {

    /**
     * 保存全局属性值
     */
    private static ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();

    private static final String DEFAULT_CONFIG_FILE = "application.properties";

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader(DEFAULT_CONFIG_FILE);


    /**
     * 获取配置
     * @see getConfig('adminPath')
     */
    public static String getConfig(String key) {
        key = key.trim();
        if (!map.containsKey(key)) {
            String value = map.get(key);
            if (value == null){
                if(!loader.getProperties().containsKey(key)){
                    PropertiesLoader commonLoader = new PropertiesLoader("common.properties");
                    value = commonLoader.getProperty(key);
                }else{
                    value = loader.getProperty(key);
                }
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
        }
        return map.get(key);
    }

    /**
     * 根据配置文件和key获取对象
     * @param key
     * @param propName
     * @return String
     * @author:    曹海兵
     * @date:      2017年1月1日 上午10:46:40
     * @throws
     */
    public static String getStringByKey(String key, String propName) {
        PropertiesLoader loaderPropName = null;
        try {
            loaderPropName = new PropertiesLoader(propName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        key = key.trim();
        if (!map.containsKey(key)) {
            //如果参数没有缓存
            String value = map.get(key);
            if (value == null){
                if(!loaderPropName.getProperties().containsKey(key)){
                    PropertiesLoader commonLoader = new PropertiesLoader("common.properties");
                    value = commonLoader.getProperty(key);
                }else{
                    value = loaderPropName.getProperty(key);
                }
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
        }
        return map.get(key);
    }

    /**
     * TODO 清除缓存
     * @author: 谢楚炎
     * @date: 2017年8月29日 下午1:55:19
     */
    public static void flush(){
        loader = new PropertiesLoader(DEFAULT_CONFIG_FILE);
        map.clear();
    }
}
