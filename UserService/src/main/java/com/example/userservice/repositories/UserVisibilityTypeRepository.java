package com.example.userservice.repositories;

import com.example.userservice.models.UserVisibilityType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserVisibilityTypeRepository extends CrudRepository<UserVisibilityType, Integer> {

    List<UserVisibilityType> findAll();

    Optional<UserVisibilityType> findOneByType(String type);


}

