package com.dist.interview.javacc.serviceapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllertest {
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
