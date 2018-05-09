package com.lish.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedLog {

    /**
     * 日志信息
     */
    String value() default "";

}
