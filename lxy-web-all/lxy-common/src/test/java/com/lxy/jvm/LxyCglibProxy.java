package com.lxy.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lxy on 08/12/2017.
 */
public class LxyCglibProxy implements MethodInterceptor {

    private LxyService lxyService;

    LxyCglibProxy(LxyService lxyService) {
        this.lxyService = lxyService;
    }

    public Lxy getCglibProxy() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(lxyService.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        Object obj = enhancer.create();
//        System.out.println(obj.getClass().getName());
//        System.out.println(obj.getClass().getSuperclass().getName() + " super!");
        return (Lxy) obj;

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // System.out.println("cglib proxy!");
        System.out.println(method.getDeclaringClass().getName());

        // return methodProxy.invokeSuper(o, objects);
        // return method.invoke(lxyService, objects);
        return methodProxy.invoke(lxyService, objects);
    }
}
