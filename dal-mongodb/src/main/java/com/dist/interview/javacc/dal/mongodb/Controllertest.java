package com.dist.interview.javacc.dal.mongodb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controllertest {
    @GetMapping("/mongo")
    public String sayHello() {
        return "Hello, World!";
    }
}
