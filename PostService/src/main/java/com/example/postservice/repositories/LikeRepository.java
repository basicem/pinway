package com.example.postservice.repositories;

import com.example.postservice.models.Hashtag;
import com.example.postservice.models.Like;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LikeRepository  extends CrudRepository<Like, Long> {

    @Query("SELECT l FROM Like l WHERE l.comment.id = :commentId")
    Iterable<Like> getByCommentId(
            @Param("commentId") Long comment_id
    );
    @Query("SELECT l FROM Like l WHERE l.comment.id = :commentId AND l.user_id = :userId ")
    Like getByCommentUserId(
            @Param("commentId") Long comment_id,
            @Param("userId") Long user_id
    );
}
