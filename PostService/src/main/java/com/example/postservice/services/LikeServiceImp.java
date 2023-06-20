package com.example.postservice.services;

import com.example.postservice.dto.UserDTO;
import com.example.postservice.exception.PinwayError;
import com.example.postservice.infrastructure.EventService;
import com.example.postservice.infrastructure.UserService;
import com.example.postservice.models.Comment;
import com.example.postservice.models.Like;
import com.example.postservice.models.Post;
import com.example.postservice.repositories.CommentRepository;
import com.example.postservice.repositories.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImp implements  LikeService{
    @Autowired
    public LikeRepository likeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public Like Create(Like like){
        try {
            Like newLike = likeRepository.save(like);

            Comment comment = newLike.getComment();
            UserDTO userDTO = userService.GetUser(newLike.getUser_id().intValue());

            if(comment.getUser_id() != newLike.getUser_id())
                eventService.LikeCreated(newLike, comment.getUser_id().intValue(), userDTO.getUsername());
            return  newLike;
        } catch (Exception e) {
            System.out.println(e);
        }
        return  null;
    }

    @Override
    public Boolean Delete (Long id){
        Like like = likeRepository.findById(id).orElse(null);
        if(like == null) {
            throw new PinwayError("Not found Post with id = " + id);
        }
        likeRepository.deleteById(id);
        return true;
    }

    public Like checkExistsByCommentLikeId(Long comment_id, Long user_id) {

        Like like = likeRepository.getByCommentUserId(comment_id,user_id);

        return  like;
    }
}
