package com.example.springmvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Despriction:
 * @Author: zhousheng
 * @CreatedTime: 2019-07-25 15:15
 * @ModifyBy:
 * @ModifyTime:
 * @ModifyDespriction:
 * @Version: V1.0.0
 */
//表示该注解加在什么位置
@Target({ElementType.TYPE, ElementType.METHOD})
//表示该注解作用于哪个阶段（编译阶段、保存到Class阶段、运行阶段）
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    // @RequestMapping(value = "")
    String value() default "";
}
