package com.chaching.aspect;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(* com.chaching.controller.*.*(..))")
    private void controllerMethods() {}

    @Before("controllerMethods()")
    public void logBeforeControllerMethodExecution(JoinPoint joinPoint) {
        if(RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String requestURI = request.getRequestURI();
            String httpMethod = request.getMethod();
            String parameters = Arrays.toString(joinPoint.getArgs());

            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();

            log.info("Before executing "+ className + "." + methodName);
            log.info("Request URI : " + requestURI);
            log.info("HTTP Method : " + httpMethod);
            log.info("Parameters : " + parameters);

        }else{
            log.warn("request not found/not valid");
        }
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfterControllerMethodExecution(JoinPoint joinPoint, Object result) {

        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        log.info("After executing " + className + "." + methodName);
        log.info("Response : " + result);

    }

    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return result;
    }

    // @AfterReturning(pointcut = "execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))", returning = "result")
    // public void logJpaRepositoryQueryExecution(JoinPoint joinPoint, Object result) {
    //     String query = joinPoint.getSignature().getName();
    //     System.out.println("Query Executed : " + query);
    // }

}
