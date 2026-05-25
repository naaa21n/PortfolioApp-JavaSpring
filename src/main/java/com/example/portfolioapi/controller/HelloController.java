package com.example.portfolioapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}