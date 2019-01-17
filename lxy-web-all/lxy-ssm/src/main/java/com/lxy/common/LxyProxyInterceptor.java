package com.lxy.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lxy on 28/01/2018.
 */
public class LxyProxyInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LxyProxyInterceptor.class);

    private Class<?> targetClass;

    private Object proxyObject;

    LxyProxyInterceptor(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    LxyProxyInterceptor() {

    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("Lxy Proxy Succeed!");
        return invocation.proceed();
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
    }

    public Object getProxyObject() {
        return proxyObject;
    }

    public void setProxyObject(Object proxyObject) {
        this.proxyObject = proxyObject;
    }
}
