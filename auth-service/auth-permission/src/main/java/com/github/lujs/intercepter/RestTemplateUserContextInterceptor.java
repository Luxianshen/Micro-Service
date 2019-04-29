package com.github.lujs.intercepter;

import com.github.lujs.user.api.model.User;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @Describe: 客户端请求拦截器
 * @Author: lujs
 * @Date: 2019/4/29 10:46
 * @Version: 1.0.0
 **/

public class RestTemplateUserContextInterceptor implements ClientHttpRequestInterceptor {

    /**
     * 拦截请求设置用户，请求地址
     * @param request
     * @param body
     * @param execution
     * @return
     * @throws IOException
     */
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        //获取用户 todo
        User user = new User("1","lisi");
        request.getHeaders().add("x-user-id",user.getId());
        request.getHeaders().add("x-user-name",user.getId());
        request.getHeaders().add("x-user-serviceName",request.getURI().getHost());

        return execution.execute(request, body);
    }
}
