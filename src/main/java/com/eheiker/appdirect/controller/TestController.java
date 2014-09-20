package com.eheiker.appdirect.controller;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eheiker.appdirect.logging.AutowiredLogger;

@RestController
public class TestController {

    @AutowiredLogger
    private Logger log;

    @RequestMapping("/hello")
    public String hello() {
        log.info("entered hello controller");

        return "world";
    }
}
