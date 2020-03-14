package com.ls.security.demo.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @className: TimeAspect
 * @description:
 * @author: liusCoding
 * @create: 2020-03-13 10:59
 */

@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.ls.security.demo.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println("time aspect start");

        Object[] args = point.getArgs();
        for (Object arg : args) {
            System.out.println("arg is :"+ arg);
        }
        long startTime  = System.currentTimeMillis();
        Object object = point.proceed();
        System.out.println("time aspect 耗时："+(System.currentTimeMillis() - startTime));
        System.out.println("time aspect end");

        return object;
    }
}
