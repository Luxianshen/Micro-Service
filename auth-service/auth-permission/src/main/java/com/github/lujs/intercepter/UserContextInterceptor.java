package com.github.lujs.intercepter;

import com.github.lujs.annotation.Permission;
import com.github.lujs.user.api.model.User;
import com.github.lujs.userapiimpl.utils.UserUtils;
import com.github.lujs.util.UserPermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Method;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过mvc 获取当前方法的权限
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Permission permission = method.getAnnotation(Permission.class);
        //查找用户
        User user = getUser(request);
        //判断权限
        if(null == user || !UserPermissionUtil.validatePermission(user,permission.value())){
            //没有权限，直接输出json流 后期可改造为页面 todo
            response.setHeader("Content-Type", "application/json");
            String noPermissionMsg = JSON.toJSONString("no permisson access service, please check!");
            response.getWriter().write(noPermissionMsg);
            response.getWriter().flush();
            response.getWriter().close();
            log.info("no permisson access service, please check!");
        }
        return true;
    }

    /**
     * 获取用户
     * @param request
     * @return 用户信息
     */
    private User getUser(HttpServletRequest request){
        String name = request.getHeader("x-user-name");
        return UserUtils.getUser(name);
    }

}
