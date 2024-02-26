package com.chaching.helper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.caching.controller.*.*(..))")
    public void loggingPointCut(){}

    @Around("loggingPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable{

        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] args = pjp.getArgs();
        log.info("method invoked "+ className+" : " + methodName + "()" + "arguments :"  + objectMapper.writeValueAsString(args) );
        Object object = pjp.proceed();
        log.info(className+" : " + methodName + "()" + "resoonse :"  + objectMapper.writeValueAsString(object) );
        return  object;

    }

}
