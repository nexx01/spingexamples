package com.example.demo.annotation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.annotation.AnnotationInAnnotation.*;

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

   // @AnnotationInAnnotation({
     //       @InAnnotation(name = "sd")
   // }   )
    @AnnotationInAnnotation(
            value2 = @InAnnotation(name = "faaaa"),
          value1 =  @InAnnotation(name = "faaaa")
    )
    public List<Integer> inAnnotation() throws InterruptedException{
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

    public Mono<List<Integer>> forParametrTest(@ForParametr(value = "d") String s) throws InterruptedException{
        System.out.println("--> before forParametrTest");
        List<Integer> ret = new ArrayList<>(Arrays.asList(100, 2, 10));
        return Mono.just(ret)
                .doOnSuccess(r-> System.out.println("forParametrTest mono "+ r));
    }

}
