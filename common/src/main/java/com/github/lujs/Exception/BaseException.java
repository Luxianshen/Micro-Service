package com.github.lujs.Exception;

import com.github.lujs.model.enums.ValueEnum;

/**
 * 全局异常
 * @author Lujs
 */
public class BaseException extends RuntimeException {

    private ValueEnum status;

    public BaseException() {
    }

    public BaseException(ValueEnum status) {
        super(status.text());
        this.status = status;
    }

    public BaseException(ValueEnum status, String message) {
        super(message);
        this.status = status;
    }

    public BaseException(ValueEnum status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(ValueEnum status, Throwable cause) {
        super(status.text(), cause);
    }

    public BaseException(ValueEnum status, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }

    public ValueEnum getStatus() {
        return status;
    }

    public void setStatus(ValueEnum status) {
        this.status = status;
    }
}
