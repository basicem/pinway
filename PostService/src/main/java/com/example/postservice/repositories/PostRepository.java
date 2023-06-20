package com.example.postservice.repositories;

import com.example.postservice.dto.PostDTO;
import com.example.postservice.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.user_id =?1")
    Iterable<Post> findByUserId(Long id);

}