package com.github.lujs.web;

import com.github.lujs.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Describe: 基础Controller
 * @Author: lujs
 * @Date: 2019/4/29 15:05
 * @Version: 1.0.0
 **/

public abstract class BaseController {

    public BaseController() {
    }

    protected BaseResponse baseResponse(boolean result) {
        return result ? new BaseResponse() : new BaseResponse(1);
    }

    protected BaseResponse successResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setData(data);
        return response;
    }

    protected BaseResponse failedResponse(Object data) {
        BaseResponse response = new BaseResponse();
        response.setFailed();
        response.setData(data);
        return response;
    }

}
