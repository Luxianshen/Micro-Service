package com.github.lujs.transmit.apiimpl.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.model.RoleApiEntity;
import com.github.lujs.transmit.api.model.User;
import com.github.lujs.transmit.api.service.RoleApiService;
import com.github.lujs.transmit.api.service.TransmitService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @Description: 请求转发控制层
 * @Author lujs
 * @Date 2019/11/25 16:36
 */
@AllArgsConstructor
@RestController
@RequestMapping("/transmit")
public class TransmitController extends BaseController {

    private final RestTemplate restTemplate;

    private final RedisTemplate redisTemplate;

    private final TransmitService transmitService;

    private final RoleApiService roleApiService;

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

    @RequestMapping("testGet")
    @Permission(action = Action.Skip)
    public Object testGet(HttpServletRequest request) {

        //截取请求后缀 获取请求实体
        QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_key", request.getHeader("apiKey"));
        ApiEntity apiEntity = transmitService.getOne(queryWrapper);
        return restTemplate.getForEntity(apiEntity.getRealUrl(), Object.class);

    }

    @RequestMapping("testPost")
    @Permission(action = Action.Skip)
    public Object testPost(HttpServletRequest request, @RequestBody Object o) {

        System.out.println("业务开始："+System.currentTimeMillis());
        /*if (ObjectUtil.isEmpty(o)) {
            return failedResponse(GlobalStatusCode.INVALID_PARAMETER);
        }*/
        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitService.getApiByKey(request.getHeader("apiKey"));

        if (ObjectUtil.isNotEmpty(apiEntity) ) { //&& StringUtils.isNotEmpty(apiEntity.getRealUrl())
            //拿到header信息
            HttpHeaders requestHeaders = new HttpHeaders();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                requestHeaders.add(key, value);
            }
            HttpEntity<String> httpEntity = new HttpEntity<String>(JSONUtil.toJsonStr(o), requestHeaders);
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(apiEntity.getRealUrl(), httpEntity, Object.class);
            System.out.println("业务结束："+System.currentTimeMillis());
            return responseEntity;
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


    /**
     * 接口分页
     */
    @RequestMapping(value = "/page")
    @Permission(action = Action.Skip)
    public BaseResponse page() {
        IPage<ApiEntity> page = new Page<>();
        return successResponse(transmitService.page(page));
    }

    /**
     * 获取接口信息
     */
    @PostMapping("/get")
    @Permission(action = Action.Skip)
    public BaseResponse get(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(transmitService.getById(request.getData()));
    }

    /**
     * 保存接口
     */
    @PostMapping("/save")
    @Permission(action = Action.Skip)
    public BaseResponse menuSave(@Valid @RequestBody BaseRequest<ApiEntity> request) {
        ApiEntity apiEntity = request.getData();
        redisTemplate.opsForHash().put("apiMap", apiEntity.getApiKey(), apiEntity.getPermissionCode());
        return successResponse(transmitService.save(apiEntity));
    }

    /**
     * 更新接口
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse update(@Valid @RequestBody BaseRequest<ApiEntity> request) {
        ApiEntity apiEntity = new ApiEntity();
        BeanUtils.copyProperties(request.getData(), apiEntity);
        boolean flag = transmitService.updateById(apiEntity);
        return successResponse(flag);
    }

    /**
     * 删除接口
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    public BaseResponse delete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        //去除缓存的key
        ApiEntity apiEntity = transmitService.getById(request.getData().getId());
        redisTemplate.opsForHash().delete("apiMap", apiEntity.getApiKey());
        return successResponse(transmitService.removeById(request.getData().getId()));
    }

    /**
     * 接口授权
     *
     * @param request
     * @return
     */
    @PostMapping("/api/grant")
    @Permission(action = Action.Skip)
    public BaseResponse grant(@Valid @RequestBody BaseRequest<RoleApiEntity> request) {
        return baseResponse(roleApiService.save(request.getData()));
    }

    /**
     * 接口授权
     *
     * @param request
     * @return
     */
    @PostMapping("/api/revoke")
    @Permission(action = Action.Skip)
    public BaseResponse revoke(@Valid @RequestBody BaseRequest<RoleApiEntity> request) {
        QueryWrapper<RoleApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", request.getData().getRoleId());
        queryWrapper.eq("api_id", request.getData().getApiId());
        return baseResponse(roleApiService.remove(queryWrapper));
    }

    /**
     * 获取用户接口权限
     */
    @PostMapping("/api/getRoleApiList")
    @Permission(action = Action.Skip)
    public List<String> getRoleApiList(@RequestBody RoleQuery roleQuery) {
        if (roleQuery.getRoles() == null || roleQuery.getRoles().size() < 1) {
            return new ArrayList<>();
        }
        return roleApiService.getRoleApiList(roleQuery);
    }

}
