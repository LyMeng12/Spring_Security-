package com.example.Srping_Security.service;

import com.example.Srping_Security.model.Usermodel;
import com.example.Srping_Security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
public class UserrService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;


    public Usermodel findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));

    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usermodel user = findByUsername(username);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
