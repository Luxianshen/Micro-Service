package com.github.lujs.Exception;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 全局异常捕获
 * @Author lujs
 * @Date 2019/12/18 9:28
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 所有异常报错
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String allExceptionHandler(Exception exception) {
        exception.printStackTrace();
        Map<String, String> errorMap = new HashMap<>(2);
        errorMap.put("stuts", "500");
        errorMap.put("message", exception.getMessage());
        return JSONUtil.toJsonStr(errorMap);
    }

}
