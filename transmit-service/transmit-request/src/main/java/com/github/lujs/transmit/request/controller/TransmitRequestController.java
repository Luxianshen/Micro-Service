package com.github.lujs.transmit.request.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.transmit.api.feign.TransmitServiceClient;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.user.api.model.User;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Description:
 * @Author lujs
 * @Date 2019/12/16 15:56
 */
@AllArgsConstructor
@RestController
@RequestMapping("/joysim/request")
public class TransmitRequestController extends BaseController {

    private final RestTemplate restTemplate;

    private final TransmitServiceClient transmitServiceClient;

    @RequestMapping("test1")
    @Permission(action = Action.Skip)
    public BaseResponse test1() {
        return successResponse("hi test1");
    }

    @PostMapping("test2")
    @Permission(action = Action.Skip)
    public BaseResponse test2(@RequestBody User user) {
        return successResponse(user);
    }

    @PostMapping("test3")
    @Permission(action = Action.Skip)
    public BaseResponse test3(User user) {
        return successResponse(user);
    }

    @GetMapping("apiGet")
    @Permission(action = Action.Skip)
    public Object apiGet(HttpServletRequest request) {

        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitServiceClient.getApiByKey(request.getHeader(CommonConstant.API_REQ));
        //携带header信息转发
        HttpHeaders requestHeader = getHeader(request);
        HttpEntity requestEntity = new HttpEntity(requestHeader);
        return restTemplate.exchange(apiEntity.getRealUrl(), HttpMethod.GET,requestEntity,Object.class);
    }

    /**
     * json格式参数转发
     * @param request
     * @param o
     * @return
     */
    @PostMapping(value = "apiPost")
    @Permission(action = Action.Skip)
    public Object apiPost(HttpServletRequest request, @RequestBody Object o) {

        if (ObjectUtil.isEmpty(o)) {
            return failedResponse(GlobalStatusCode.INVALID_PARAMETER);
        }
        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitServiceClient.getApiByKey(request.getHeader(CommonConstant.API_REQ));

        if (ObjectUtil.isNotEmpty(apiEntity) && StringUtils.isNotEmpty(apiEntity.getRealUrl())) {
            //拿到header信息
            HttpHeaders requestHeaders = getHeader(request);
            HttpEntity<String> httpEntity = new HttpEntity<String>(JSONUtil.toJsonStr(o), requestHeaders);
            return restTemplate.postForEntity(apiEntity.getRealUrl(), httpEntity, Object.class);
        }
        return failedResponse("接口不存在");
    }

    /**
     * form格式参数转发 两接口不合并 大并发下 性能更优
     * @param request
     * @return
     */
    @PostMapping(value = "apiForm")
    @Permission(action = Action.Skip)
    public Object apiForm(HttpServletRequest request) {

        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitServiceClient.getApiByKey(request.getHeader(CommonConstant.API_REQ));

        if (ObjectUtil.isNotEmpty(apiEntity) && StringUtils.isNotEmpty(apiEntity.getRealUrl())) {
            //拿到header信息
            HttpHeaders requestHeaders = getHeader(request);
            //获取parameter信息
            Map<String, String[]> params = request.getParameterMap();
            if (params != null && params.size() > 0) {
                MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
                params.forEach((k,v)->map.add(k,v[0]));
                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, requestHeaders);
                return restTemplate.postForEntity(apiEntity.getRealUrl(), requestEntity, Object.class);
            }
            return failedResponse("参数为空！");
        }
        return failedResponse("接口不存在");
    }

    /**
     * 获取请求header信息
     * @param request
     * @return
     */
    private HttpHeaders getHeader(HttpServletRequest request){
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            requestHeaders.add(key, value);
        }
        return requestHeaders;
    }
}
