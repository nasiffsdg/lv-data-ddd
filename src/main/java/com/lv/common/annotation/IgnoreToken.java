package com.lv.common.annotation;

import java.lang.annotation.*;

/**
 * 服务内部调用api鉴权忽略token（动态放权）
 * @author QiangZai
 * @version 1.0
 * @date 2023/7/14 10:59 PM
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreToken {

    boolean value() default true;
}
