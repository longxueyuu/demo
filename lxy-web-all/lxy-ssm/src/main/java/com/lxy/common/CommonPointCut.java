package com.lxy.common;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by lxy on 06/12/2017.
 */
public class CommonPointCut {

    @Pointcut("@annotation(com.lxy.annotation.Dispatch)")
    public void dispatchedMethodSet(){}

}
