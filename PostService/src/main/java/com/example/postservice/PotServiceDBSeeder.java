package com.example.postservice;

import com.example.postservice.repositories.CommentRepository;
import com.example.postservice.repositories.HashtagRepository;
import com.example.postservice.repositories.LikeRepository;
import com.example.postservice.repositories.PostRepository;
import com.example.postservice.models.Comment;
import com.example.postservice.models.Hashtag;
import com.example.postservice.models.Like;
import com.example.postservice.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class PotServiceDBSeeder {
    private HashtagRepository hashtagRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public PotServiceDBSeeder(HashtagRepository hashtagRepository,
                              LikeRepository likeRepository,
                              CommentRepository commentRepository,
                              PostRepository postRepository,
                              JdbcTemplate jdbcTemplate){
        this.hashtagRepository = hashtagRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    private  void seedPostTable() {
        String sql = "SELECT id, name FROM Hashtag LIMIT 1";
        List<Post> p = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( p == null || p.size() == 0){
            Post p1 = new Post();
            p1.setTitle("Cool stuff");
            p1.setDescription("Just a cool image");
            p1.setImage_path("some/path");
            p1.setUser_id(Long.valueOf(1));
            p1.setPin_counter(0);
            p1.setCreated_at(LocalDateTime.now());

            Post p2 = new Post();
            p2.setTitle("Awesome stuff");
            p2.setDescription("Just an awesome image");
            p2.setImage_path("some/path2");
            p2.setUser_id(Long.valueOf(1));
            p2.setPin_counter(0);
            p2.setCreated_at(LocalDateTime.now());

            postRepository.save(p1);
            postRepository.save(p2);
        }
    }
    private void seedHashtagTable() {
        String sql = "SELECT id, name FROM Hashtag LIMIT 1";
        List<Hashtag> h = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if( h == null || h.size() == 0){
            Post post1 = postRepository.findById(Long.valueOf(1)).orElse(null);
            if( post1 == null) return;
            Post post2 = postRepository.findById(Long.valueOf(2)).orElse(null);
            if( post1 == null) return;

            Hashtag h1 = new Hashtag();
            h1.setName("Nature");
            h1.setPin_counter(0);
            h1.setHash_counter(0);

            Hashtag h2 = new Hashtag();
            h2.setName("Portrait");
            h2.setPin_counter(0);
            h2.setHash_counter(0);

            Hashtag h3 = new Hashtag();
            h3.setName("Pastel");
            h3.setPin_counter(0);
            h3.setHash_counter(0);

            this.hashtagRepository.save(h1);
            this.hashtagRepository.save(h2);
            this.hashtagRepository.save(h3);

            Set<Hashtag> set = new HashSet<>();
            set.add(h1);
            set.add(h2);
            post1.setHashtags(set);
            this.postRepository.save(post1);
            set.add(h3);
            post2.setHashtags(set);
            this.postRepository.save(post2);
        }
    }
    private void seedCommentTable(){
        String sql = "SELECT id, content FROM Comment LIMIT 1";
        List<Comment> com = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (com == null || com.size() == 0) {
            Post post = postRepository.findById(Long.valueOf(1)).orElse(null);
            if( post == null) return;
            Comment com1 = new Comment();
            com1.setPost(post);
            com1.setContent("Some comment");
            com1.setUser_id(Long.valueOf(1));

            Comment com2 = new Comment();
            com2.setPost(post);
            com2.setContent("Another comment");
            com2.setUser_id(Long.valueOf(1));

            commentRepository.save(com1);
            commentRepository.save(com2);
        }
    }
    private void seedLikeTable() {
        String sql = "SELECT id FROM Likes l LIMIT 1";
        List<Like> like = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
        if (like == null || like.size() == 0) {
            Comment com1 = commentRepository.findById(Long.valueOf(1)).orElse(null);
            if(com1 == null) return;
            Like l1 = new Like();
            l1.setComment(com1);
            l1.setUser_id(Long.valueOf(1));
            Like l2 = new Like();
            l2.setComment(com1);
            l2.setUser_id(Long.valueOf(1));

            likeRepository.save(l1);
            likeRepository.save(l2);
        }
    }
    @EventListener
    public  void seed(ContextRefreshedEvent event){
        seedPostTable();
        seedCommentTable();
        seedLikeTable();
        seedHashtagTable();
    }

}
