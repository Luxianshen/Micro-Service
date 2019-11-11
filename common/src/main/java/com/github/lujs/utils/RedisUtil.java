package com.github.lujs.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 缓存工具类
 * 默认添加项目名称做为前缀，防止多项目冲突
 * 缓存穿透 阿里云处理 暂不考虑
 * @Author lujs
 * @Date 2019/7/1 14:59
 */
@Component
public class RedisUtil {

    private static final Logger log = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * redisTemplate对象
     */
    private static RedisTemplate<String,Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        if (null == redisTemplate) {
            log.info("Redis初始化配置失败，请检查配置项");
        } else {
            log.info("Redis初始化配置注入成功！");
        }
        RedisUtil.redisTemplate = (RedisTemplate)redisTemplate;
    }

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("set() is error : {}", e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("set() is error : {}", e);
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public static boolean setExpire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error("expire() is error : {}", e);
            return false;
        }
    }


    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error("hasKey() is error : {}", e);
            return false;
        }
    }


    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object, Object> hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hashSet(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            log.error("hmset() is error : {}", e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hashSet(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("hmset() is error : {}", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            log.error("hset() is error : {}", e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hSet(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("hset() is error : {}", e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hashDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hasHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public static double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public static double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public static Set<Object> setGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.error("sGet() is error : {}", e);
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public static boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            log.error("sHasKey() is error : {}", e);
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long setSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            log.error("sSet() is error : {}", e);
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public static long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) {
                setExpire(key, time);
            }
            return count;
        } catch (Exception e) {
            log.error("sSetAndTime() is error : {}", e);
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public static long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            log.error("setRemove() is error : {}", e);
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    public static List<Object> listGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.error("lGet() is error : {}", e);
            return null;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key 键
     * @return
     */
    public static List<Object> listGet(String key) {
        return listGet(key, 0, -1);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean listSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            log.error("lSet() is error : {}", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean listSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("lSet() is error : {}", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    public static boolean listSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            log.error("lSet() is error : {}", e);
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public static boolean listSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                setExpire(key, time);
            }
            return true;
        } catch (Exception e) {
            log.error("lSet() is error : {}", e);
            return false;
        }
    }

    /**
     * 获取对象
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> T getObjectBean(String key, Class<T> clazz) {

        String value = (String) redisTemplate.opsForValue().get(key);
        return parseJson(value, clazz);
    }

    /**
     * 存放对象
     *
     * @param key
     * @param obj
     */
    public static <T> void setObjectBean(String key, T obj) {
        if (obj == null) {
            return;
        }
        String value = toJson(obj);
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存放对象并返回该对象
     *
     * @param key
     * @param obj
     */
    public static <T> T getAndSet(String key, T obj, Class<T> clazz) {
        if (obj == null) {
            return getObjectBean(key, clazz);
        }
        String value = (String) redisTemplate.opsForValue().getAndSet(key, toJson(obj));
        return parseJson(value, clazz);
    }

    /**
     * 原子自增
     *
     * @param key
     * @return
     */
    public static long generate(String key) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.incrementAndGet();
    }

    /**
     * A原子自增
     *
     * @param key
     * @param expireTime
     * @return
     */
    public static long generate(String key, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.incrementAndGet();
    }

    /**
     * 原子自增
     *
     * @param key
     * @param increment
     * @return
     */
    public static long generate(String key, int increment) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        return counter.addAndGet(increment);
    }

    /**
     * 原子自增
     *
     * @param key
     * @param increment
     * @param expireTime
     * @return
     */
    public static long generate(String key, int increment, Date expireTime) {
        RedisAtomicLong counter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        counter.expireAt(expireTime);
        return counter.addAndGet(increment);
    }

    /**
     * 序列化对象为JSONString
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.SortField);
    }

    /**
     * 序列化JSON为ObjectBean
     *
     * @param json
     * @return
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * 序列化List对象
     *
     * @param key
     * @param clazz
     * @return
     */
    public static <T> List<T> getList(String key, Class<T> clazz) {
        try {
            Object cache = get(key);
            if (null != cache && StringUtils.isNotBlank(cache.toString())) {
                List<T> cacheList = JSONArray.parseArray(JSONArray.parseArray(cache.toString()).toJSONString(), clazz);
                return cacheList;
            }
        } catch (Exception e) {
            log.error("redis查询异常，e:", e);
        }
        return null;
    }

    /**
     * 将List作为JSONString存入缓存
     *
     * @param key
     * @param list
     */
    public static <T> void setList(String key, List<T> list) {
        if (StringUtils.isBlank(key) || list.size()==0) {
            return;
        }
        set(key, toJson(list));
    }

    /**
     * 删除缓存
     */
    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 模糊删除 单个
     */
    public static void deleteFuzzy(String key) {
        redisTemplate.delete(redisTemplate.keys(key + "*"));
    }

    /**
     * 模糊删除 批量
     */
    public static void deleteFuzzy(Set<String> keys) {
        //keys.forEach(x -> redisTemplate.delete(redisTemplate.keys(x + "*")));
    }

    /**
     * 批量删除
     */
    public static void deleteBatch(Set<String> keys) {
        //keys.forEach(x -> redisTemplate.delete(redisTemplate.keys(x)));
    }

    /**
     * 全删 慎用
     */
    public static void deleteAll() {
        redisTemplate.delete(redisTemplate.keys("*"));
    }

}
