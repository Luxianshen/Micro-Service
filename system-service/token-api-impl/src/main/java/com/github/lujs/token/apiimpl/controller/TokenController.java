package com.github.lujs.token.apiimpl.controller;

import com.github.lujs.Exception.BaseException;
import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.constant.GlobalStatusCode;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.token.api.model.LoginInfo;
import com.github.lujs.token.api.service.TokenService;
import com.github.lujs.token.api.service.ValidCodeService;
import com.github.lujs.utils.JwtUtil;
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
public class TokenController {

    private final TokenService targetService;

    private final Producer producer;

    private final ValidCodeService validCodeService;

    @GetMapping("/getToken/{userName}")
    public String get(@PathVariable("userName") String userName) {
        return null;
    }

    /**
     * 系统登陆方法
     */
    @PostMapping("/login")
    @Permission(action = Action.Skip)
    @ResponseBody
    public Object login(LoginInfo loginInfo) {
        BaseResponse baseResponse = new BaseResponse();
        //密码加密传输 todo
        if (StringUtils.isAllEmpty(loginInfo.getUserName(), loginInfo.getPassWord())) {
            //返回参数不全 提示
            throw new BaseException(GlobalStatusCode.INVALID_PARAMETER);
        }
        String token = targetService.login(loginInfo);
        Map<String, Object> re = new HashMap<>();
        re.put("access_token", token);
        re.put("expires_in", 5000);
        re.put("refresh_token", token);
        re.put("scope", "read");
        re.put("token_type", "bearer");
        baseResponse.setData(re);
        return re;
    }

    /**
     * 系统推出方法
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

    @RequestMapping("test")
    public String test(){
        return "hi,i'm token";
    }

}
