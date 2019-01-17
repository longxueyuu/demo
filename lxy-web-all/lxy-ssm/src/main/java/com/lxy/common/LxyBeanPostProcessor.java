package com.lxy.common;

import com.lxy.annotation.LxyProxy;
import org.apache.commons.lang.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lxy on 28/01/2018.
 */
@Component
public class LxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private static final Logger logger = LoggerFactory.getLogger(LxyBeanPostProcessor.class);
    private BeanFactory beanFactory;
    private final Set<Object> earlyProxyReferences =
            Collections.newSetFromMap(new ConcurrentHashMap<Object, Boolean>(16));
    private final Map<Object, Class<?>> proxyTypes = new ConcurrentHashMap<>(16);

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        if (this.proxyTypes.isEmpty()) {
            return null;
        }
        Object cacheKey = getCacheKey(beanClass, beanName);
        return this.proxyTypes.get(cacheKey);
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        Object cacheKey = getCacheKey(bean.getClass(), beanName);
        this.earlyProxyReferences.add(cacheKey);
        return createProxyIfNecessary(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean != null) {
            Object cacheKey = getCacheKey(bean.getClass(), beanName);
            if (!this.earlyProxyReferences.contains(cacheKey)) {
                return createProxyIfNecessary(bean, beanName);
            }
        }
        return bean;
    }

    /**
     * Build a cache key for the given bean class and bean name.
     *
     * 这里没有采用AbstractAutoProxyCreator中的(return beanClass.getName() + "_" + beanName)方式，原因是当有多层代理时，在getEarlyBeanReference中的bean为代理bean，从而
     * bean.getClass()为代理类的class类型，如com.lxy.service.FansMeetingService$$EnhancerBySpringCGLIB$$91ffaa12_fansMeetingService，
     * 而在postProcessAfterInitialization的bean为原始bean，bean.getClass()为原始类型，如com.lxy.service.FansMeetingService；
     * 从而导致同一个bean在getEarlyBeanReference方法中生成的cacheKey和postProcessAfterInitialization中生成cacheKey不一致，
     * 导致在postProcessAfterInitialization再一次创建了bean的代理对象(而实际上不应该再创建代理对象，因为已经在getEarlyBeanReference中创建了)，
     * 进一步导致循环依赖解决失败，抛出(throw new BeanCurrentlyInCreationException（"Bean with name '" + beanName + "' has been injected into other beans
     * [" +StringUtils.collectionToCommaDelimitedString(actualDependentBeans) +"] in its raw version as part of a circular reference, but has eventually been " +"wrapped.)
     *
     * @param beanClass the bean class
     * @param beanName the bean name
     * @return the cache key for the given class and name
     */
    protected Object getCacheKey(Class<?> beanClass, String beanName) {
        if (StringUtils.hasLength(beanName)) {
            return (FactoryBean.class.isAssignableFrom(beanClass) ?
                    BeanFactory.FACTORY_BEAN_PREFIX + beanName : beanName);
        } else {
            return beanClass;
        }
    }

    protected BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    public Object createProxyIfNecessary(Object bean, String beanName) {
        Class targetClass = AopUtils.getTargetClass(bean);
        if (isNeedLxyProxy(targetClass)) {
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setTarget(bean);
            LxyProxyInterceptor lxyProxyInterceptor;
            Advisor advisor;
            if (AopUtils.isJdkDynamicProxy(bean)){
                ((List<Class<?>> )ClassUtils.getAllInterfaces(bean.getClass()))
                .forEach(iface -> {
                    proxyFactory.addInterface(iface);
                });
                lxyProxyInterceptor = new LxyProxyInterceptor(targetClass);
                advisor = new DefaultPointcutAdvisor(new LxyProxyPointCut(), lxyProxyInterceptor);
            } else {
                lxyProxyInterceptor = new LxyProxyInterceptor();
                advisor = new DefaultPointcutAdvisor(new LxyProxyPointCut(), lxyProxyInterceptor);
            }
            proxyFactory.addAdvisor(advisor);
            Object beanProxy = proxyFactory.getProxy();
            lxyProxyInterceptor.setProxyObject(beanProxy);
            return beanProxy;
        }


        return bean;
    }

    public boolean isNeedLxyProxy(Class<?> clazz) {
        Optional<Method> optional = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.getAnnotation(LxyProxy.class) != null)
                .findFirst();
        if (optional.orElse(null) != null) {
            return true;
        }
        return false;
    }
}
