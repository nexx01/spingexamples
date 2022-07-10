package com.example.demo.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Aspect
@Component
@Order(1)
public class AnnotionAspect1 {


    @Around("@annotation(com.example.demo.annotation.AroundAndAroundAnotation)")
    public Object validationMonoAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("first around");
//        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] signatureArgs = joinPoint.getArgs();

//        joinPoint.proceedd();
     //   System.out.println("end first around");;
//
        return ((Mono)joinPoint.proceed()).doOnSuccess(d-> System.out.println("end first around"));
//        System.out.println("Annotation work");
//        return joinPoint.proceed();
    }

}
