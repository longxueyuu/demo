package com.example.domain.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class AspectAdvice {

    @Pointcut("execution(public * com.example.domain.task.TimerTask.*(..))")
    private void timerTask() {}


    @Around("timerTask()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("TimeTask: around: start");

        var obj = joinPoint.proceed();

        log.info("TimeTask: around: end");
        return obj;
    }


    @Pointcut("@annotation(com.example.domain.advice.AOPTarget)")
    private void annotationMethod() {}

    @Before("annotationMethod()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("annotation: before: done");
    }

    @After("annotationMethod()")
    public void doAfter(JoinPoint joinPoint) throws Throwable {
        log.info("annotation: after: done");
    }

    @AfterReturning(pointcut = "annotationMethod()", returning="result")
    public void doAfterReturn(JoinPoint joinPoint, Object result) throws Throwable {
        log.info("annotation: afterReturn: done! {}", result);
    }
}
