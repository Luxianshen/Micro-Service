package com.github.lujs.intercepter;

import com.alibaba.fastjson.JSON;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.util.UserPermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
@Component
@Slf4j
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        UserContextInterceptor.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取当前方法的权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        //存在访问路径
        if (permission != null) {
            if (permission.action() == Action.Skip) {
                //无需权限访问
                return true;
            } else {
                //查找用户信息
                UserInfo userInfo = getUserInfo(request);
                //判断权限
                if (null == userInfo || !UserPermissionUtil.validatePermission(userInfo, permission.value())) {
                    //没有权限，直接输出json流 后期可改造为页面 todo
                    response.setHeader("Content-Type", "application/json");
                    String noPermissionMsg = JSON.toJSONString("no permission access service, please check!");
                    response.getWriter().write(noPermissionMsg);
                    response.getWriter().flush();
                    response.getWriter().close();
                    log.info("no permission access service, please check!");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 获取用户
     *
     * @param request
     * @return 用户信息
     */
    private UserInfo getUserInfo(HttpServletRequest request) {
        String id = request.getHeader(CommonConstant.REQUEST_ID_HEADER);
        String name = request.getHeader(CommonConstant.REQUEST_NAME_HEADER);
        if (StringUtils.isNotEmpty(name)) {
            String tokenKey = CommonConstant.TOKEN_CODE + name + id;
            return (UserInfo) redisTemplate.opsForValue().get(tokenKey);
        }
        return null;
    }

}
