package com.example.demo.config;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OperationAnnotation {
    String content() default "";

    int sysType() default 0;

    int opType() default 0;

    String action() default "";
}
