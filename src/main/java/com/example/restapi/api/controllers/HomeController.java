package com.example.restapi.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/home")
    public String getHome() {
        return "Welcome to Maven Spring Boot..";
    }
}
