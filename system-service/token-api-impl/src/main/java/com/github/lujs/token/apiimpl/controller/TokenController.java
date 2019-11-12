package com.github.lujs.token.apiimpl.controller;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.token.api.model.LoginInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: jwt token生成控制层
 * @Author: lujs
 * @Date: 2019/4/29 9:43
 * @Version: 1.0.0
 **/

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/getToken/{userName}")
    public String get(@PathVariable("userName") String userName){
        return null;
    }

    /**
     * 系统登陆方法
     */
    @PostMapping("/login")
    @Permission(action = Action.Skip)
    @ResponseBody
    public Object login(LoginInfo loginInfo){
        BaseResponse baseResponse = new BaseResponse();
        //密码加密传输 todo
        if(StringUtils.isAllEmpty(loginInfo.getUserName(),loginInfo.getPassWord())){
            //返回参数不全 提示
            throw new BaseException(GlobalStatusCode.INVALID_PARAMETER);
        }
        String token = tokenService.login(loginInfo);
        Map<String,Object> re = new HashMap<>();
        re.put("access_token",token);
        re.put("expires_in",5000);
        re.put("refresh_token",token);
        re.put("scope","read");
        re.put("token_type","bearer");
        baseResponse.setData(re);
        return re;
    }

}
