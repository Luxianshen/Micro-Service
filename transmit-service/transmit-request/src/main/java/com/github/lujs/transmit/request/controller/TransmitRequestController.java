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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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

    @RequestMapping("apiGet")
    @Permission(action = Action.Skip)
    public Object apiGet(HttpServletRequest request) {

        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitServiceClient.getApiByKey(request.getHeader(CommonConstant.API_REQ));
        return restTemplate.getForEntity(apiEntity.getRealUrl(), Object.class);

    }

    @RequestMapping("apiPost")
    @Permission(action = Action.Skip)
    public Object apiPost(HttpServletRequest request, @RequestBody Object o) {

        if (ObjectUtil.isEmpty(o)) {
            return failedResponse(GlobalStatusCode.INVALID_PARAMETER);
        }
        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitServiceClient.getApiByKey(request.getHeader(CommonConstant.API_REQ));

        if (ObjectUtil.isNotEmpty(apiEntity) && StringUtils.isNotEmpty(apiEntity.getRealUrl())) {
            //拿到header信息
            HttpHeaders requestHeaders = new HttpHeaders();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                requestHeaders.add(key, value);
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(JSONUtil.toJsonStr(o), requestHeaders);
            return restTemplate.postForEntity(apiEntity.getRealUrl(), httpEntity, Object.class);
        }
        return failedResponse("接口不存在");

        //获取parameter信息
        /*Map<String, ?> params = request.getParameterMap();
        if (params != null && params.size() > 0) {
            JSONObject jsonParams = JSONUtil.parseObj(params);
            HttpEntity<JSONObject> requestEntity = new HttpEntity<>(jsonParams, requestHeaders);
            return sendPostRequest(apiEntity.getRealUrl(), requestEntity);
        }*/
        //return sendPostRequest(apiEntity.getRealUrl(), requestEntity);
    }
}
