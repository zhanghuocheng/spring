package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncTest {

    /**
     * java异步方法需要满足三个条件
     * 1.没有在@SpringBootApplication启动类当中添加注解@EnableAsync注解。
     * 2.异步方法使用注解@Async的返回值只能为void或者Future。
     * 3.没有走Spring的代理类。因为@Transactional和@Async注解的实现都是基于Spring的AOP，而AOP的实现是基于动态代理模式实现的。那么注解失效的原因就很明显了，有可能因为调用方法的是对象本身而不是代理对象，因为没有经过Spring容器。
     */
    @Async
    public void test(){
        log.info("test2");
    }
}
