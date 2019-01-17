package com.lxy.jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lxy on 08/12/2017.
 */
public class LxyProxy implements InvocationHandler{


    private LxyService lxyService;

    LxyProxy(LxyService lxyService) {
        this.lxyService = lxyService;
    }

    public LxyService getProxy() {
        Object obj = Proxy.newProxyInstance(lxyService.getClass().getClassLoader(),
                lxyService.getClass().getInterfaces(), this);
//        System.out.println(obj.getClass().getName());
//        System.out.println(obj.getClass().getSuperclass().getName() + " super!");
        return (LxyService) obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println("java dynamic proxy!");
        System.out.println(method.getDeclaringClass().getName());
        return method.invoke(lxyService, args);
    }
}
