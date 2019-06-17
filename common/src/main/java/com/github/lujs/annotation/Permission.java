package com.github.lujs.annotation;

import java.lang.annotation.*;

/**
 * @Description: 权限切面
 * @Author: lujs
 * @Data: 2019/6/15 9:47
 * @version: 1.0.0
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String value() default "";

    Action action() default Action.Normal;
}
