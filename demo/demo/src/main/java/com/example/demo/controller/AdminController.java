package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String adminHome() {
        return "Welcome Admin";
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        Enumeration<String> attributeNames = request.getAttributeNames();
        while(attributeNames.hasMoreElements()) {
            System.out.println(attributeNames.nextElement());
        }

        return (CsrfToken) request.getAttribute("_csrf");
    }
}

