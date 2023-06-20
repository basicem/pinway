package com.example.collectionservice.repositories;

import com.example.collectionservice.models.Collection;
import com.example.collectionservice.models.CollectionVisibilityType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CollectionRepository extends CrudRepository<Collection, Integer> {
    List<Collection> findAll();

    @Transactional
    @Modifying
    @Query("update Collection c set c.numOfPosts= c.numOfPosts + 1 where c.id = :id")
    Integer increaseNumOfPosts(Integer id);

    List<Collection> findAllByIsDeletedAndIdIn(Boolean deleted, Iterable<Integer> id);

    Optional<Collection> findByIdAndIsDeleted(Integer id, Boolean deleted);

    List<Collection> findAllByIsDeletedAndUserId(Boolean deleted, Integer id);

    List<Collection> findAllByIsDeletedAndUserIdAndCollectionVisibilityType_Id(Boolean deleted, Integer id, Integer typeId);

}