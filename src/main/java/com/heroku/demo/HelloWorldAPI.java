package com.heroku.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "shopee")
public class HelloWorldAPI {

    @GetMapping
    public String hello(){
        return "Hello world";
    }
}
