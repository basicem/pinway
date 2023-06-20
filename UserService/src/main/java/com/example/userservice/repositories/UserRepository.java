package com.example.userservice.repositories;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();
    List<User> findByUsernameOrEmail(String username, String email);
    User findByUsername(String username);


}