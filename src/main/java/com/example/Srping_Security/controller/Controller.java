package com.example.Srping_Security.controller;

import com.example.Srping_Security.Config.JwtService;
import com.example.Srping_Security.dto.AuthRequest;
import com.example.Srping_Security.model.Usermodel;
import com.example.Srping_Security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/acc")
    public void GetUser(@RequestParam String username, @RequestParam String password) {
        Usermodel user = new Usermodel();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(true);
        userRepository.save(user);

    }
    @PostMapping("/authenticate")
    public void Authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authenticate.isAuthenticated()) {
            jwtService.generateToken(authRequest.getUsername());
        }

    }


}
