package com.yml.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理
 *
 * Created by Yuming-Liu
 * 日期： 2018-08-13
 * 时间： 21:23
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public Object doProxy(ProxyChain proxyChain) throws Throwable {

        Object result = null;

        Class<?> cls = proxyChain.getTargetClass();

        Method method = proxyChain.getTargetMethod();

        Object[] params = proxyChain.getMethodParams();

        begain();
        try {
            if (intercept(cls, method, params)) {
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                after(cls, method, params, result);
            }else{
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(cls, method, params, e);
            throw e;
        }finally {
            end();
        }
        return result;
    }

    private void end() {

    }

    private void error(Class<?> cls, Method method, Object[] params, Throwable e) {
    }

    private void after(Class<?> cls, Method method, Object[] params, Object result) {
    }

    public void begain(){
    }

    private boolean intercept(Class<?> cls, Method method, Object[] params) {
        return true;
    }

    private void before(Class<?> cls, Method method, Object[] params) {
    }


}
