package com.example.demo.controller;

import com.example.demo.async.AsyncTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoController implements CommandLineRunner {


    @Autowired
    private AsyncTest asyncTest;


    @Override
    public void run(String... args) throws Exception {
        log.info("test");
        for (int i = 0; i <10 ; i++) {
            asyncTest.test();
        }
    }
}
