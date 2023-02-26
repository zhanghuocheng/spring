package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@Component
@Slf4j
public class DemoController implements CommandLineRunner {



    @Resource(name = "SpExecutor")
    private Executor executor;

    @Override
    public void run(String... args) throws Exception {
        log.info("test");
        for (int i = 0; i <10 ; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("test2");
                }
            });
        }
    }
}
