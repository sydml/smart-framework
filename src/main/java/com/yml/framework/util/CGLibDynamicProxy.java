package com.yml.framework.util;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Yuming-Liu
 * 日期： 2018-08-08
 * 时间： 23:16
 */
public class CGLibDynamicProxy implements MethodInterceptor {

    private static CGLibDynamicProxy CGLIB_PROXY = null;

    private CGLibDynamicProxy() {}

    public static CGLibDynamicProxy getCgLibProxy() {
        return CGLIB_PROXY ==null? new CGLibDynamicProxy():CGLIB_PROXY;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls,this);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj, args);
        after();
        return result;
    }
    private void before() {
        System.out.println("调用前");
    }

    private void after() {
        System.out.println("调用后");
    }
}
