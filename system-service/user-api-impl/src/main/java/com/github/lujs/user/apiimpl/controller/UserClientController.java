package com.github.lujs.user.apiimpl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.model.BaseRequest;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.model.request.PrimaryKeyRequest;
import com.github.lujs.user.api.model.UserClient;
import com.github.lujs.user.api.model.UserClientInfo;
import com.github.lujs.user.api.service.UserClientService;
import com.github.lujs.web.BaseController;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Describe: 客户端控制层
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/v1/userClient")
@AllArgsConstructor
public class UserClientController extends BaseController {

    private final UserClientService userClientService;

    /**
     * 客户端分页
     *
     * @return
     */
    @RequestMapping(value = "/page")
    @Permission(action = Action.Skip)
    public BaseResponse page() {
        IPage<UserClient> page = new Page<>();
        return successResponse(userClientService.page(page));
    }

    /**
     * 获取客户端信息
     */
    @PostMapping("/get")
    @Permission(action = Action.Skip)
    public BaseResponse get(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(userClientService.getById(request.getData()));
    }

    /**
     * 保存客户端
     */
    @PostMapping("/save")
    @Permission(action = Action.Skip)
    public BaseResponse save(@Valid @RequestBody BaseRequest<UserClient> request) {
        if (null == userClientService.getClientByAgentId(request.getData().getAgentId())) {
            return successResponse(userClientService.saveClient(request.getData()));
        } else {
            return failedResponse("客户端已存在！");
        }
    }

    /**
     * 更新客户端
     */
    @PostMapping("/update")
    @Permission(action = Action.Skip)
    public BaseResponse update(@Valid @RequestBody BaseRequest<UserClient> request) {
        UserClient userClient = new UserClient();
        BeanUtils.copyProperties(request.getData(), userClient);
        boolean flag = userClientService.updateById(userClient);
        return successResponse(flag);
    }

    /**
     * 删除客户端
     */
    @PostMapping("/delete")
    @Permission(action = Action.Skip)
    public BaseResponse delete(@Valid @RequestBody BaseRequest<PrimaryKeyRequest> request) {
        return successResponse(userClientService.removeById(request.getData().getId()));
    }

    /**
     * 根据客户端id,获取客户端agentId
     */
    @PostMapping("/checkUserClient")
    @Permission(action = Action.Skip)
    public UserClient checkUserClient(@RequestBody UserClientInfo userClientInfo) {

        UserClient checkClient = userClientService.getClientByAgentId(userClientInfo.getAgentId());
        if (null != checkClient) {
            //比较加密串
            String checkAuth = DigestUtils.md5Hex("agentId=" + checkClient.getAgentId() + "&randomStr=" + userClientInfo.getAgentAuth() +
                    "&randomStr=" + userClientInfo.getRandomStr() + "&key=" + checkClient.getMacKey());
            if (checkAuth.equals(userClientInfo.getSign())) {
                return checkClient;
            }
        }
        return null;
    }

}

