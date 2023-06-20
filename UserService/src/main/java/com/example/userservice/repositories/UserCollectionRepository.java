package com.example.userservice.repositories;

import com.example.userservice.models.User;
import com.example.userservice.models.UserCollection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCollectionRepository extends CrudRepository<UserCollection, Integer> {
    List<UserCollection> getByCollectionId(Integer collectionId);
}