package com.github.lujs.Exception;

/**
 * @Description:
 * @Author: lujs
 * @Data: 2019/4/2823:48
 * @version: 1.0.0
 */
public class PermissionException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public PermissionException(String msg){
        super(msg);
    }
}
