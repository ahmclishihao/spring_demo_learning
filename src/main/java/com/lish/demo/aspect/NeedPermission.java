package com.lish.demo.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验权限
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NeedPermission {

    String[] value() default "";

    String role() default "";

}
