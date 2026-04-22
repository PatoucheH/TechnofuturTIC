package be.bstorm.tf2026javaspringmvc.bll.aspects.perfomance;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerfMonitoring {

    @Around("@annotation(performance)")
    public Object monitoring(ProceedingJoinPoint joinPoint, Performance performance) {
        Long start = System.currentTimeMillis();

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        Long end = System.currentTimeMillis();
        log.debug("Method " + joinPoint.getSignature() + " took " + (end - start) + " millis");

        if (end - start > performance.millis()) {
            log.error("Method " + joinPoint.getSignature() + " took too long");
        }

        return result;
    }
}
