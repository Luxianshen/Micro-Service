package com.github.lujs.token.apiimpl.controller;

import com.github.lujs.annotation.Action;
import com.github.lujs.annotation.Permission;
import com.github.lujs.model.BaseResponse;
import com.github.lujs.token.apiimpl.service.ValidCodeService;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.code.kaptcha.Producer;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Description: 验证码控制层
 * @Author lujs
 * @Date 2019/7/12 11:35
 */
@AllArgsConstructor
@RestController
@RequestMapping("/valid/code")
public class ValidCodeController {

    private final Producer producer;

    private final ValidCodeService validCodeService;

    /**
     * 创建验证码
     * @return 返回验证码
     */
    @GetMapping("/{random}")
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
}
