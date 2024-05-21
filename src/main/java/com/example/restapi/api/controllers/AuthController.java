package com.example.restapi.api.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.api.models.User;
import com.example.restapi.api.services.JwtService;
import com.example.restapi.api.services.UserService;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginUser) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUsername());
        final String token = jwtService.generateToken(userDetails);

        Map<String, Object> response = new HashMap<>();

        response.put("status", "OK");
        response.put("token", token);
        response.put("user", userDetails.getUsername());

        return response;
    }

}
