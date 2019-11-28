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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
@Component
@Slf4j
public class ApiContextInterceptor extends HandlerInterceptorAdapter {

    private static List<String> whiteRoute = new ArrayList<String>(Arrays.asList("192.168.4.79:8079"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("认证开始：" + System.currentTimeMillis());
        //过滤白名单
        if (whiteRoute.contains(request.getHeader("host"))) {
            return true;
        }
        //获取当接口的权限
        String apiKey = request.getHeader("apiKey");
        String id = request.getHeader("x-user-id");
        String name = request.getHeader("x-user-name");
        //存在访问路径
        if (!StringUtils.isAnyEmpty(apiKey, id, name)) {
            //查找用户信息
            List<String> apiPermissionList = getUserInfo(id, name);
            //判断权限
            if (null == apiPermissionList || !validatePermission(apiPermissionList, apiKey)) {
                //没有权限，直接输出json流 后期可改造为页面
                response.setHeader("Content-Type", "application/json");
                String noPermissionMsg = JSON.toJSONString("no permission access service, please check!");
                response.getWriter().write(noPermissionMsg);
                response.getWriter().flush();
                response.getWriter().close();
                log.info("no permission access service, please check!");
            }
            System.out.println("认证结束：" + System.currentTimeMillis());
            return true;
        }
        return false;
    }

    /**
     * 获取用户
     *
     * @param id id
     * @param name name
     * @return 用户信息
     */
    private List<String> getUserInfo(String id, String name) {

        String tokenKey = CommonConstant.TOKEN_CODE + id + name;
        return ((UserInfo) RedisUtil.get(tokenKey)).getApiList();
    }

    /**
     * 验证权限方法
     *
     * @return boolean
     * @Param user 用户
     * @Param tagCode 当前访问的权限标识
     */
    private static boolean validatePermission(List<String> apiPermissionList, String tagCode) {

        //获取接口权限
        String tagPermission = (String) RedisUtil.hashGet("apiMap", tagCode);
        if (StringUtils.isNotEmpty(tagPermission) && apiPermissionList.contains(tagPermission)) {
            return true;
        }
        return false;
    }


}
