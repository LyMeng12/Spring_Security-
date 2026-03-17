package com.example.Srping_Security.Service.Impl;

import com.example.Srping_Security.Service.UserService;
import com.example.Srping_Security.dto.UserRequest;
import com.example.Srping_Security.dto.UserResponse;
import com.example.Srping_Security.entity.UserEntity;
import com.example.Srping_Security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceIpml implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void addUser(UserRequest user) {
        UserEntity userRequest = new UserEntity();
        userRequest.setUsername(user.getUserName());
        userRequest.setPassword(passwordEncoder.encode(user.getUserPassword()));
        userRequest.setRole(user.getUserRole());
        userRepository.save(userRequest);
    }

    @Override
    public void updateUser(Long id, UserRequest user) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            log.error("User not found",id);
        }
        userEntity.get().setUsername(user.getUserName());
        userEntity.get().setPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(userEntity.get());
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            log.error("User not found",id);
        }
        userRepository.delete(userEntity.get());
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()){
            log.error("User not found",id);
            return null;
        }
        UserResponse user = new UserResponse();
        user.setUserId(userEntity.get().getId());
        user.setUserName(userEntity.get().getUsername());
        user.setUserPassword(userEntity.get().getPassword());
        user.setUserRole(userEntity.get().getRole());
        return user;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (UserEntity userEntity : userEntities){
            UserResponse user = new UserResponse();
            user.setUserId(userEntity.getId());
            user.setUserName(userEntity.getUsername());
            user.setUserPassword(userEntity.getPassword());
            user.setUserRole(userEntity.getRole());
            userResponses.add(user);
        }
        return userResponses;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build();
    }
}
