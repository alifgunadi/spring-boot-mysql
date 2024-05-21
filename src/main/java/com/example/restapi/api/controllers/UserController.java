package com.example.restapi.api.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.api.exceptions.ResourceNotFoundException;
import com.example.restapi.api.models.User;
import com.example.restapi.api.repositories.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @GetMapping(value = "/list/users")
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            user.setPassword(null);
        }

        return users;
    }

    @GetMapping(value = "/detail/users/{id}")
    public ResponseEntity<User> findUser(@PathVariable long id) {
        User userOptional = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found" + id));

        return ResponseEntity.ok(userOptional);
    }

    @PostMapping(value = "/register/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return ResponseEntity.ok("Register user is successfully.");
    }

    @PostMapping(value = "/update/user")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        long id = user.getId();
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found:" + id));

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUpdatedAt(new Date());

        userRepository.save(updatedUser);

        return ResponseEntity.ok("User updated successfully");

    }

    @DeleteMapping(value = "/delete/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        User deletedUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found:" + id));

        userRepository.delete(deletedUser);

        return ResponseEntity.ok("User deleted successfully.");
    }

}
