package com.example.postservice.services;

import com.example.postservice.models.Like;

public interface LikeService {
     Like Create(Like like);
     Boolean Delete (Long id);

     Like checkExistsByCommentLikeId(Long comment_id, Long like_id);
}
