package com.example.Srping_Security.Service;

import com.example.Srping_Security.dto.UserRequest;
import com.example.Srping_Security.dto.UserResponse;
import com.example.Srping_Security.entity.UserEntity;

import java.util.List;

public interface UserService {

    void addUser(UserRequest user);
    void updateUser(Long id ,UserRequest user);

    void deleteUser(Long id);

    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();

}
