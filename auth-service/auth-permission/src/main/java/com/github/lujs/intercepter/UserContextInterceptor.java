package com.github.lujs.intercepter;

import com.github.lujs.Exception.PermissionException;
import com.github.lujs.user.api.model.User;
import com.github.lujs.util.UserPermissionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
public class UserContextInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse respone, Object arg2) throws Exception {

        //todo 查找用户
        User user = getUser(request);
        //判断权限
        if(!UserPermissionUtil.validatePermission(user,request)){
            //没有权限，直接输出json流 后期可改造为页面 todo
            respone.setHeader("Content-Type", "application/json");
            String noPermissionMsg = JSON.toJSONString("no permisson access service, please check");
            respone.getWriter().write(noPermissionMsg);
            respone.getWriter().flush();
            respone.getWriter().close();
            throw new PermissionException("no permisson access service, please check");
        }
        //缓存权限 todo
        return true;
    }

    private User getUser(HttpServletRequest request){
        String id = request.getHeader("x-user-id");
        String name = request.getHeader("x-user-name");
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

}
