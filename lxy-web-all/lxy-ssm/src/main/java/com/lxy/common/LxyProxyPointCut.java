package com.lxy.common;

import com.lxy.annotation.LxyProxy;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by lxy on 28/01/2018.
 */
public class LxyProxyPointCut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getAnnotation(LxyProxy.class) != null;
    }
}
