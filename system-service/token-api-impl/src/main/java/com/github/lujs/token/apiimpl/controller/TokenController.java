package com.github.lujs.token.apiimpl.controller;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.service.TokenService;
import com.github.lujs.token.api.service.ValidCodeService;
import com.github.lujs.token.apiimpl.config.TokenProperties;
import com.github.lujs.user.api.feign.UserServiceClient;
import com.github.lujs.user.api.model.UserClient;
import com.github.lujs.user.api.model.UserClientInfo;
import com.github.lujs.utils.JwtUtil;
import com.github.lujs.web.BaseController;
import com.google.code.kaptcha.Producer;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Describe: jwt token生成控制层
 * @Author: lujs
 * @Date: 2019/4/29 9:43
 * @Version: 1.0.0
 **/
@AllArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController extends BaseController {

    private final TokenProperties tokenProperties;

    private final TokenService targetService;

    private final Producer producer;

    private final ValidCodeService validCodeService;

    private final UserServiceClient userServiceClient;


    /**
     * 系统登陆方法
     */
    @PostMapping("/login")
    @Permission(action = Action.Skip)
    @ResponseBody
    public BaseResponse login(LoginInfo loginInfo) {

        //密码加密传输 todo
        if (StringUtils.isAllEmpty(loginInfo.getUserName(), loginInfo.getPassWord())) {
            //返回参数不全 提示
            throw new BaseException(GlobalStatusCode.INVALID_PARAMETER);
        }
        String token = targetService.login(loginInfo);
        Map<String, Object> re = new HashMap<>(5);
        re.put("access_token", token);
        re.put("expires_in", tokenProperties.getTokenTime());
        re.put("refresh_token", token);
        re.put("scope", "read");
        re.put("token_type", "bearer");
        return successResponse(re);
    }

    /**
     * 系统退出方法
     */
    @PostMapping("/removeToken")
    @Permission(action = Action.Skip)
    @ResponseBody
    public void removeToken(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (StringUtils.isNotEmpty(token)) {
            JwtUtil.removeToken(token);
        }
    }

    /**
     * 创建验证码
     *
     * @return 返回验证码
     */
    @GetMapping("/code/{random}")
    @Permission(action = Action.Skip)
    public void produceCode(@PathVariable String random, HttpServletResponse response) throws Exception {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        validCodeService.saveImageCode(random, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 客户端获取token方法
     */
    @PostMapping("/getToken")
    @Permission(action = Action.Skip)
    public BaseResponse getToken(@RequestBody UserClientInfo userClientInfo) {

        //校验客户端
        UserClient userClient = userServiceClient.checkUserClient(userClientInfo);
        if (null != userClient) {
            //生成客户端token
            return successResponse(targetService.generateClientToken(userClient));
        }else {
            return failedResponse(GlobalStatusCode.INVALID_PARAMETER);
        }
    }

}
