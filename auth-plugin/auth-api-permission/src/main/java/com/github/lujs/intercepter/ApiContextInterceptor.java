package com.github.lujs.intercepter;

import com.github.lujs.Exception.PermissionException;
import com.github.lujs.Exception.status.PermissionStatusCode;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.user.api.model.UserClientInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * @Describe: 用户权限拦截器
 * @Author: lujs
 * @Date: 2019/4/29 13:15
 * @Version: 1.0.0
 **/
@Component
@Slf4j
public class ApiContextInterceptor extends HandlerInterceptorAdapter {

    private static RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        ApiContextInterceptor.redisTemplate = redisTemplate;
    }

    /**
     * 本地转发 或者网关转发
     */
    private static List<String> whiteRoute = new ArrayList<String>(Arrays.asList("localhost","127.0.0.1:8078"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //过滤白名单 x-user-host为空 即feign内部调用
        if (whiteRoute.contains(request.getHeader(CommonConstant.REQUEST_HOST_HEADER)) || StringUtils.isEmpty(request.getHeader(CommonConstant.REQUEST_HOST_HEADER))) {
            return true;
        }
        //获取当接口的权限
        String apiKey = request.getHeader(CommonConstant.API_REQ);
        String id = request.getHeader(CommonConstant.REQUEST_ID_HEADER);
        String name = request.getHeader(CommonConstant.REQUEST_NAME_HEADER);
        //存在访问路径
        if (!StringUtils.isAnyEmpty(apiKey, id, name)) {
            //查找用户信息
            List<String> apiPermissionList = getUserInfo(id, name);
            //判断权限
            if (null == apiPermissionList || !validatePermission(apiPermissionList, apiKey)) {
                log.info("no permission access service, please check!");
                throw new PermissionException(PermissionStatusCode.NO_PERMISSION);
                //sendMessage(response,PermissionStatusCode.NO_PERMISSION);
                //return false;
            }
            return true;
        }
        throw new PermissionException(PermissionStatusCode.NO_TOKEN);
        //sendMessage(response,PermissionStatusCode.NO_TOKEN);
        //return false;
    }

    /**
     * 获取用户
     *
     * @param id id
     * @param name name
     * @return 用户信息
     */
    private List<String> getUserInfo(String id, String name) {

        String tokenKey = CommonConstant.API_TOKEN_CODE+ name + id ;
        return ((UserClientInfo) redisTemplate.opsForValue().get(tokenKey)).getApiList();
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
        String tagPermission = (String) redisTemplate.opsForHash().get("apiMap", tagCode);
        if (StringUtils.isNotEmpty(tagPermission) && apiPermissionList.contains(tagPermission)) {
            return true;
        }
        return false;
    }

    public static void sendMessage(HttpServletResponse response, PermissionStatusCode statusCode) throws Exception {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        Map<String, String> map = new HashMap<>();
        map.put("status",statusCode.code().toString());
        map.put("msg", statusCode.text());
        writer.write(map.toString());
    }


}
