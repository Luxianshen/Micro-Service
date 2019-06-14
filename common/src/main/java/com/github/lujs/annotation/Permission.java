package com.github.lujs.annotation;

import java.lang.annotation.*;

/**
 * @author: lujs
 * @date: 2019-01-30 17:06
 * @desc:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String value() default "";

    Action action() default Action.Normal;
}
