package com.example.demo.controller;

import com.example.demo.async.AsyncTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController {


    @Autowired
    private AsyncTest asyncTest;

    @GetMapping("/demo")
    public String demo() {
        log.info("test");
        for (int i = 0; i <10 ; i++) {
            asyncTest.test();
        }
        return "hello";
    }
}
