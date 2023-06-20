package com.example.collectionservice.repositories;

import com.example.collectionservice.models.CollectionVisibilityType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionVisibilityTypeRepository extends CrudRepository<CollectionVisibilityType, Integer> {
    List<CollectionVisibilityType> findAll();
    Optional<CollectionVisibilityType> findOneByType(String type);
}
