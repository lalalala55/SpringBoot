package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @GetMapping("/")
    public String home(){
        return "Home Page - Welcome";
    }

    @GetMapping("/demo")
    public String demo(){
        return "Demo page";
    }
}
