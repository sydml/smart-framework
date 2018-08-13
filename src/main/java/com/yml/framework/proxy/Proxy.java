package com.yml.framework.proxy;

/**
 * 代理接口
 *
 * Created by Yuming-Liu
 * 日期： 2018-08-13
 * 时间： 20:56
 */
public interface Proxy {
    /**
     * 执行链式代理
     */

    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
