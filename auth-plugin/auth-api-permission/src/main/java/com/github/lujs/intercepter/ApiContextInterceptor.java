package com.github.lujs.intercepter;

import com.alibaba.fastjson.JSON;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.user.api.model.UserInfo;
import com.github.lujs.util.UserPermissionUtil;
import com.github.lujs.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
@Component
@Slf4j
public class ApiContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //过滤白名单
        if(StringUtils.isNotEmpty(request.getHeader("origin"))&& request.getHeader("origin").equals("http://localhost:9527")){
            return true;
        }
        //过滤feign
        if(StringUtils.isNotEmpty(request.getHeader("user-agent"))&&request.getHeader("user-agent").equals("Java/1.8.0_161")){
            return true;
        }
        //获取当接口的权限
        String apiKey = request.getHeader("apiKey");
        //存在访问路径
        if (apiKey != null) {
            //查找用户信息
            UserInfo userInfo = getUserInfo(request);
            //判断权限
            if (null == userInfo || !UserPermissionUtil.validatePermission(userInfo, apiKey)) {
                //没有权限，直接输出json流 后期可改造为页面
                response.setHeader("Content-Type", "application/json");
                String noPermissionMsg = JSON.toJSONString("no permission access service, please check!");
                response.getWriter().write(noPermissionMsg);
                response.getWriter().flush();
                response.getWriter().close();
                log.info("no permission access service, please check!");
            }
            return true;
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
        String id = request.getHeader("x-user-id");
        String name = request.getHeader("x-user-name");
        if (StringUtils.isNotEmpty(name)) {
            String tokenKey = CommonConstant.TOKEN_CODE + id + name;
            return (UserInfo) RedisUtil.get(tokenKey);
        }
        return null;
    }


}
