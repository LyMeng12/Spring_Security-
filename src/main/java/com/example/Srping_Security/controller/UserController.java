package com.example.Srping_Security.controller;

import com.example.Srping_Security.Service.UserService;
import com.example.Srping_Security.dto.UserRequest;
import com.example.Srping_Security.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse == null) {
            log.info("User with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userResponse);
    }
    @GetMapping("/All")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAllUsers();
        if (userResponses == null) {
            log.info("All users not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(userResponses);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok().body(userRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse == null) {
            log.info("User with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User Deleted Successfully!");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse == null) {
            log.info("User with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(id, userRequest);
        return ResponseEntity.ok().body(userRequest);
    }

}