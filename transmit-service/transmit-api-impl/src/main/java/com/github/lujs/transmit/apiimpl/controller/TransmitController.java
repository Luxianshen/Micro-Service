package com.github.lujs.transmit.apiimpl.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.auth.api.feign.AuthServiceClient;
import com.github.lujs.auth.api.model.Role.RoleQuery;
import com.github.lujs.constant.CommonConstant;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.transmit.api.model.ApiEntity;
import com.github.lujs.transmit.api.model.ApiEntityDto;
import com.github.lujs.transmit.api.model.ClientApiEntity;
import com.github.lujs.transmit.api.model.RoleApiEntity;
import com.github.lujs.transmit.api.service.ClientApiService;
import com.github.lujs.transmit.api.service.RoleApiService;
import com.github.lujs.transmit.api.service.TransmitService;
import com.github.lujs.user.api.feign.UserServiceClient;
import com.github.lujs.user.api.model.User;
import com.github.lujs.web.BaseController;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

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

    private final ClientApiService clientApiService;

    private final AuthServiceClient authServiceClient;

    private final UserServiceClient userServiceClient;

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
        QueryWrapper<ApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("api_key", request.getHeader(CommonConstant.API_REQ));
        ApiEntity apiEntity = transmitService.getOne(queryWrapper);
        return restTemplate.getForEntity(apiEntity.getRealUrl(), Object.class);

    }

    @Hystrix
    @RequestMapping("apiPost")
    @Permission(action = Action.Skip)
    public Object apiPost(HttpServletRequest request, @RequestBody Object o) {

        if (ObjectUtil.isEmpty(o)) {
            return failedResponse(GlobalStatusCode.INVALID_PARAMETER);
        }
        //截取请求后缀 获取请求实体
        ApiEntity apiEntity = transmitService.getApiByKey(request.getHeader("apiKey"));

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
    public BaseResponse save(@Valid @RequestBody BaseRequest<ApiEntity> request) {
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
        return successResponse(transmitService.updateApi(apiEntity));
    }

    /**
     * 删除接口
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    @CacheEvict(value = "transmits", key = "#apiEntity.apiKey")
    public BaseResponse delete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        //去除缓存的key
        ApiEntity apiEntity = transmitService.getById(request.getData().getId());
        return successResponse(transmitService.deleteById(apiEntity));
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
        return successResponse(roleApiService.save(request.getData()));
    }

    /**
     * 取消接口授权
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
        if (roleApiService.remove(queryWrapper)) {
            List<String> userList = authServiceClient.getRoleUserList(request.getData().getRoleId());
            List<String> agentIds = userServiceClient.getUserAgentIds(userList);
            if (ObjectUtil.isNotEmpty(agentIds)) {
                Set<String> removeData = new HashSet<>(agentIds);
                removeData.forEach(x -> redisTemplate.delete(CommonConstant.TOKEN_CODE + x));
            }
            return successResponse(GlobalStatusCode.SUCCESS);
        } else {
            return failedResponse(GlobalStatusCode.FAILED);
        }
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

    /**
     * 获取用户接口权限
     */
    @PostMapping("/api/getClientApiList")
    @Permission(action = Action.Skip)
    public List<String> getClientApiList(@RequestParam("clientId") Long clientId) {
        return clientApiService.getClientApiList(clientId);
    }

    /**
     * 获取客户端接口情况
     */
    @PostMapping("/api/findClientPermissionTree")
    @Permission(action = Action.Skip)
    public BaseResponse findClientPermissionTree(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {

        List<ApiEntityDto> clientApiPermission = clientApiService.getClientApiPermissions(request.getData().getId());
        return successResponse(clientApiPermission);
    }

    /**
     * 接口授权
     *
     * @param request
     * @return
     */
    @PostMapping("/grant")
    @Permission(action = Action.Skip)
    public BaseResponse clientGrant(@Valid @RequestBody BaseRequest<ClientApiEntity> request) {
        return successResponse(clientApiService.save(request.getData()));
    }

    /**
     * 取消接口授权
     *
     * @param request
     * @return
     */
    @PostMapping("/revoke")
    @Permission(action = Action.Skip)
    public BaseResponse clientRevoke(@Valid @RequestBody BaseRequest<ClientApiEntity> request) {
        QueryWrapper<ClientApiEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("client_id", request.getData().getClientId());
        queryWrapper.eq("api_id", request.getData().getApiId());
        if (clientApiService.remove(queryWrapper)) {

            redisTemplate.delete(CommonConstant.API_TOKEN_CODE + request.getData().getAgentId());
            return successResponse(GlobalStatusCode.SUCCESS);
        } else {
            return failedResponse(GlobalStatusCode.FAILED);
        }
    }

}
