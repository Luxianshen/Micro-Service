package com.github.lujs.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

/**
 * @Description: 全局异常捕获
 * @Author lujs
 * @Date 2019/12/18 9:28
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 所有异常报错
     *
     * @param exception
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = Exception.class)
    public LinkedHashMap<String, Object> allExceptionHandler(Exception exception) {
        exception.printStackTrace();
        LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>(2);
        try{
            //自己的抛出的异常
            BaseException e = (BaseException) exception;
            errorMap.put("status", e.getStatus().code());
            errorMap.put("message", e.getStatus().text());
        }catch (Exception e){
            errorMap.put("status", "500");
            errorMap.put("message", e.getMessage());
        }
        return errorMap;
    }

}
