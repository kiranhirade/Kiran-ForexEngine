package com.example.forex.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String sayHello() {
        return "Hello and Welcome to the Forex engine application. You can create a new Forex by making a POST request to /api/forexs endpoint.";
    }
}
