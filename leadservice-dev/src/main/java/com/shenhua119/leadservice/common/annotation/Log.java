package com.shenhua119.leadservice.common.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 * 系统日志注解
 * </pre>
 * @author liuchanghai
 * @create 2019-01-08 0:54 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Log {
    String value() default "";
}
