package com.zouwu.trade.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(OperationLog)")
    public void log() {
    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {


    }

    @AfterReturning(value = "log() && @annotation(operationLog)")
    public void doAfterReturn(JoinPoint joinPoint, OperationLog operationLog) {

    }


    @Around(value = "log() && @annotation(operationLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        System.out.println("-------Start-------" + operationLog.value());
        Object o = joinPoint.proceed();
        System.out.println("-------End-------" + operationLog.value());
        return o;
    }


}
