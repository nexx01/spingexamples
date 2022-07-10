package com.example.demo.annotation;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TestAnnotationClass {

    @TraceAble
    public void methodForTracable(){
        System.out.println("---> It is methodForTracable");
    }

    @AnnotationWithOneParametr
    public List<Integer> test() throws InterruptedException{
        System.out.println("--> AnnotationWithOneParametr");
        List<Integer> ret = new ArrayList<>(Arrays.asList(100, 2, 10));
        return ret;
    }

    @WithListInParametr(reviews = {"1","2","3"})
    public List<Integer> withListInParametr() throws InterruptedException{
        System.out.println("--> AnnotationWithOneParametr");
        List<Integer> ret = new ArrayList<>(Arrays.asList(100, 2, 10));
        return ret;
    }

    @AnnotationValidationWithMono
    @AroundAndAroundAnotation
    public Mono<List<Integer>> withListInParametrMono(String s) throws InterruptedException{
        System.out.println("--> AnnotationWithOneParametr");
        List<Integer> ret = new ArrayList<>(Arrays.asList(100, 2, 10));
        return Mono.just(ret)
                .doOnSuccess(r-> System.out.println("mono "+ r));
    }

    @beforeAspectValidationWithMono
    public Mono<List<Integer>> beforeWithListInParametrMono(String s) throws InterruptedException{
        System.out.println("--> before AnnotationWithOneParametr");
        List<Integer> ret = new ArrayList<>(Arrays.asList(100, 2, 10));
        return Mono.just(ret)
                .doOnSuccess(r-> System.out.println("before mono "+ r));
    }
}
