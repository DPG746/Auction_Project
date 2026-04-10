package com.springproject.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //return data to http request
public class MyController {

    @GetMapping("/test")  //when user call or text this method will be called
    public String testapi(){
        return "App is working";

    }
}
