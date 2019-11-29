package com.github.lujs.Exception;

import com.github.lujs.Exception.status.PermissionStatusCode;

/**
 * @Description:
 * @Author: lujs
 * @Data: 2019/4/28 23:48
 * @version: 1.0.0
 */
public class PermissionException extends BaseException {

    private static final long serialVersionUID = 1L;

    public PermissionException(PermissionStatusCode status) {
        super(status);
    }

    public PermissionException(PermissionStatusCode status, String message) {
        super(status, message);
    }

    public PermissionException(PermissionStatusCode status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public PermissionException(PermissionStatusCode status, Throwable cause) {
        super(status, cause);
    }

    public PermissionException(PermissionStatusCode status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(status, message, cause, enableSuppression, writableStackTrace);
    }
}
