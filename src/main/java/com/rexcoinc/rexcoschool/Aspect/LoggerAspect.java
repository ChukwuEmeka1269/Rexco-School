package com.rexcoinc.rexcoschool.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.rexcoinc.rexcoschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeTaken = Duration.between(start, finish).toMillis();
        log.info("Time taken to executed " + joinPoint.getSignature().toString() + " method is :" + timeTaken + "ms");
        log.info(joinPoint.getSignature().toString() + " method execution end.");
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.rexcoinc.rexcoschool.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception){
        log.error(joinPoint.getSignature() + " An exception happened due to " + exception.getMessage());
    }
}
