package com.example.demo.logtrace.thred;

import com.example.demo.logtrace.Constants;
import com.example.demo.logtrace.TraceLogUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

@Configuration
public class MyExecutor {

    @Bean("SpExecutor")
    public Executor getAsyncExecutor() {
        // 对线程池进行包装，使之支持traceId透传
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor() {
            @Override
            public <T> Future<T> submit(Callable<T> task) {
                // 传入线程池之前先复制当前线程的MDC
                return super.submit(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
            @Override
            public void execute(Runnable task) {
                super.execute(ThreadMdcUtil.wrap(task, MDC.getCopyOfContextMap()));
            }
        };
        executor.setCorePoolSize(10);
        executor.initialize();
        return executor;
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                // 实际执行前导入对应请求的MDC副本
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                if (MDC.get(Constants.LOG_TRACE_ID) == null) {
                    MDC.put(Constants.LOG_TRACE_ID, TraceLogUtils.getTraceId());
                }
                try {
                    return callable.call();
                } finally {
                    MDC.clear();
                }
            }
        };
    }
}
