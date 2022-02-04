package com.codeup.halfguard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloCapstoneController {

    @GetMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "Hello from Half-G capstone!";
    }
}




