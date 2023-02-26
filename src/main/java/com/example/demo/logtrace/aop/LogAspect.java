package com.example.demo.logtrace.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Aspect
public class LogAspect {

    /**
     * 限额限日志次的 trace id
     */
    private static final String TRACE_ID = "TRACE_ID";

    /**
     * 拦截入口下所有的 public方法
     */
    @Pointcut("execution(public * com.example.demo..*(..))")
    public void pointCut() {
    }

    /**
     * 拦截处理
     *
     * @param point point 信息
     * @return result
     * @throws Throwable if any
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //添加 MDC
        MDC.put(TRACE_ID, UUID.randomUUID().toString());
        Object result = point.proceed();
        //移除 MDC
        MDC.remove(TRACE_ID);
        return result;
    }

}