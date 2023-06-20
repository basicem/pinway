package com.example.collectionservice.repositories;

import com.example.collectionservice.models.Collection;
import com.example.collectionservice.models.CollectionPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CollectionPostRepository extends CrudRepository<CollectionPost, Integer> {

    CollectionPost findByCollectionIdAndPostId(Integer collectionId, Long postId);

}