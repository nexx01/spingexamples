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
@Order(2)
public class AnnotionAspect {

    @Around("@annotation(com.example.demo.annotation.TraceAble)")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("---------------");
        System.out.println("Annotation work");
        return joinPoint.proceed();
    }

    @Pointcut(value = "execution(* *.*(..))")
    public void allMethods() {

    }

    @Around("@annotation(com.example.demo.annotation.AnnotationWithOneParametr)")
    public Object sort(ProceedingJoinPoint joinPoint) throws Exception, Throwable {
        Object proceed = joinPoint.proceed();
// retrieve the passed parameter in annotation
        AnnotationWithOneParametr s =

                ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AnnotationWithOneParametr.class);
        System.out.print(s.order());
// sort according to the input parameter
        if (proceed instanceof List) {
            if (s.order().equals("ASC")) Collections.sort((List) proceed);
            else Collections.sort((List) proceed, Collections.reverseOrder());
            return proceed;
        }
        return proceed;
    }


    @Around("@annotation(com.example.demo.annotation.WithListInParametr)")
    public Object listString(ProceedingJoinPoint joinPoint) throws Throwable {
        WithListInParametr annotationParametr =
                ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(WithListInParametr.class);

        System.out.println("--> WithListInParametr annotation " + Arrays.toString(annotationParametr.reviews()));
        System.out.println("Annotation work");
        return joinPoint.proceed();
    }

    @Around("@annotation(com.example.demo.annotation.AnnotationValidationWithMono)")
    public Object validationMono(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("second around");
//        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] signatureArgs = joinPoint.getArgs();

      return  Mono.just((String)signatureArgs[0])
                .filterWhen(f->{
                    if(f!="l"){
                        return Mono.error(new RuntimeException());
                    }
                    return Mono.just(true);
                })
                        .flatMap(f-> {
                            try {
                                System.out.println("---->flatmap");
                                return (Mono<List>)joinPoint.proceed();
                            } catch (Throwable e) {
                                throw new RuntimeException(e);
                            }
                        })
              .doOnSuccess(d-> System.out.println("end second around\"))"))

              ;
//
//        System.out.println("Annotation work");
//        return joinPoint.proceed();
    }



   // @Around("@annotation(com.example.demo.annotation.AnnotationValidationWithMono)")
    public Object validationMonoAround(ProceedingJoinPoint joinPoint) throws Throwable {

//        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] signatureArgs = joinPoint.getArgs();

        return  Mono.just((String)signatureArgs[0])
                .filterWhen(f->{
                    if(f!="l"){
                        return Mono.error(new RuntimeException());
                    }
                    return Mono.just(true);
                })
                .flatMap(f-> {
                    try {
                        System.out.println("---->flatmap");
                        return (Mono<List>)joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                })
                .doOnSuccess(d-> System.out.println("after"))
                ;
//
//        System.out.println("Annotation work");
//        return joinPoint.proceed();
    }

    @Before("@annotation(com.example.demo.annotation.beforeAspectValidationWithMono)")
    public void validationBeforeMono(JoinPoint joinPoint ) throws Throwable {

//        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
        Object[] signatureArgs = joinPoint.getArgs();

           Mono.just((String)signatureArgs[0])
                .filterWhen(f->{
                    if(f!="l"){
                        return Mono.error(new RuntimeException());
                    }
                    return Mono.just(true);
                })
                .flatMap(f-> {
                    try {
                        System.out.println("----> before flatmap");
//                        return (Mono<List>)joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                    return(Mono<List>) joinPoint;
                })
              // .block()
                ;
//
//        System.out.println("Annotation work");
//        return joinPoint.proceed();
    }
}
