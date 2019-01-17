package com.lxy.common;

import com.lxy.annotation.Dispatch;
import com.lxy.domain.GeneralRequestParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by lxy on 04/12/2017.
 */
@Aspect
@Component
public class UserAspect {

    @Around("CommonPointCut.dispatchedMethodSet()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof GeneralRequestParam) {
                MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
                Dispatch dispatch = methodSignature.getMethod().getAnnotation(Dispatch.class);
                ((GeneralRequestParam) arg).setName("aspect-" + dispatch.name());
                ((GeneralRequestParam) arg).setDispatch(true);
            }
        }
        return pjp.proceed();
    }
}
