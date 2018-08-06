package com.yml.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-06
 * 时间： 23:47
 */
public class Handler {
    /**
     * Controller
     */
    private Class<?> controllerClass;

    /**
     * RequestMapping 方法
     */
    private Method requestMappingMethod;

    public Handler(Class<?> controllerClass, Method requestMappingMethod) {
        this.controllerClass = controllerClass;
        this.requestMappingMethod = requestMappingMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getRequestMappingMethod() {
        return requestMappingMethod;
    }
}
