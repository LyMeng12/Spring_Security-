package com.example.Srping_Security.repository;

import com.example.Srping_Security.model.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usermodel, Long> {
    Optional<Usermodel> findByUsername(String username);
}
