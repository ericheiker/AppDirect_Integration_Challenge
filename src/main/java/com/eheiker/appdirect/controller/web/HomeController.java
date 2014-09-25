package com.eheiker.appdirect.controller.web;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eheiker.appdirect.logging.AutowiredLogger;

@Controller
public class HomeController {

    @AutowiredLogger
    private Logger log;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


}
