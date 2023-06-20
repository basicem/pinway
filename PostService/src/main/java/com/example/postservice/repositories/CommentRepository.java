package com.example.postservice.repositories;

import com.example.postservice.models.Comment;
import com.example.postservice.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.post.id =?1")
    List<Comment> findByPost(Long post_id);
}
