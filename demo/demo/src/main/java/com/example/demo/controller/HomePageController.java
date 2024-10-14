package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomePageController {
   /*
        - this method acts as a RestController annotation method because we added ResponseBody here
    */
    @GetMapping("/")
    @ResponseBody // without this annotation it will throw error like "Home Page - welcomme.html" not found like that
    public String home(){
        return "Home Page - Welcome";
    }

    @GetMapping("/login")
    public String demo(){
        return "login";
    }
}
