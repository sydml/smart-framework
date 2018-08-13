package com.yml.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解
 *
 * Created by Yuming-Liu
 * 日期： 2018-08-13
 * 时间： 20:52
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解
     */
    Class<? extends Annotation> value();
}
