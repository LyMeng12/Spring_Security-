package com.example.Srping_Security.controller;

import com.example.Srping_Security.model.Usermodel;
import com.example.Srping_Security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/acc")
    public void GetUser(@RequestParam String username, @RequestParam String password) {
        Usermodel user = new Usermodel();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(true);
        userRepository.save(user);

    }

}
