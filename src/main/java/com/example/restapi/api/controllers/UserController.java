package com.example.restapi.api.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.api.models.User;
import com.example.restapi.api.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/list/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/detail/users/{id}")
    public User findUser(@PathVariable long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(value = "/register/user")
    public String saveUser(@RequestBody User user) {
        try {
            User createUser = userRepository.save(user);

            Map<String, Object> result = new HashMap<>();
            result.put("status", "OK");
            result.put("message", "");
            result.put("result", createUser);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writeValueAsString(result);

            return jsonResult;
        } catch (JsonProcessingException e) {
            return "Error.." + e;
        }

    }

    @PostMapping(value = "/update/user")
    public String updateUser(@RequestBody User user) {
        try {
            long id = user.getId();
            User updatedUser = userRepository.findById(id).get();
            ZoneId zoneId = ZoneId.of("Asia/Jakarta");
            LocalDateTime now = LocalDateTime.now(zoneId);
            Date updatedAt = Date.from(now.atZone(zoneId).toInstant());

            updatedUser.setUsername(user.getUsername());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setUpdatedAt(updatedAt);

            userRepository.save(updatedUser);

            Map<String, String> result = new HashMap<>();
            result.put("status", "OK");
            result.put("message", "Updated successfully.");
            result.put("result", "{}");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writeValueAsString(result);

            return jsonResult;

        } catch (JsonProcessingException ex) {
            return "Error" + ex;
        }
    }

    @DeleteMapping(value = "/delete/user/{id}")
    public String deleteUser(@PathVariable long id) {
        User deletedUser = userRepository.findById(id).get();

        userRepository.delete(deletedUser);

        return "User deleted successfully.";
    }

}
