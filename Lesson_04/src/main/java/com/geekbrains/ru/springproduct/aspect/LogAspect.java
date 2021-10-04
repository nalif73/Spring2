package com.geekbrains.ru.springproduct.aspect;


import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    StopWatch stopWatch = new StopWatch();

    @Before("execution(* com.geekbrains.ru.springproduct.service.ProductService.*(..))")
        public void beforeCallingMethod (JoinPoint joinPoint) {
        stopWatch.start();
        System.out.println(joinPoint.getTarget().getClass().getSimpleName() + " запущен метод " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.geekbrains.ru.springproduct.service.ProductService.*(..))")
    public void afterCallingMethod (JoinPoint joinPoint) {
        stopWatch.stop();
        System.out.println(joinPoint.getTarget().getClass().getSimpleName() + " метод " + joinPoint.getSignature().getName()
                + " выполнен за "+ stopWatch.getTime() + " мс");
        stopWatch.reset();
    }


}
