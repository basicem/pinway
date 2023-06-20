package com.example.postservice.services;

import com.example.postservice.dto.FilterDTO;
import com.example.postservice.dto.PostDTO;
import com.example.postservice.dto.PostResponseDTO;
import com.example.postservice.dto.UserDTO;
import com.example.postservice.exception.PinwayError;
import com.example.postservice.models.Hashtag;
import com.example.postservice.models.Post;
import com.example.postservice.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Post Create(Post post){
        Post newPost = postRepository.save(post);
        return  newPost;
    }
    @Override
    public  Iterable<PostResponseDTO> List(){
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<PostResponseDTO> res = new ArrayList<PostResponseDTO>();
        for (Post post: posts) {
            Long param = post.getUser_id();
            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://user-service/api/users/{id}",
                    UserDTO.class, param);
            UserDTO userDTO = responseEntity.getBody();
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setImage_path(post.getImage_path());
            postDTO.setTitle(post.getTitle());
            postDTO.setUser_id(post.getUser_id());
            postDTO.setDescription(post.getDescription());
            postDTO.setHashtagNames(post.getHashtags().stream().map(hashtag -> {
                return hashtag.getName();
            } ).collect(Collectors.toSet()));
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            postResponseDTO.setPostDTO(postDTO);
            postResponseDTO.setUserDTO(userDTO);
            res.add(postResponseDTO);
        }
        Iterable<PostResponseDTO>  response = res;
        return  response;
    }
    @Override
    public  Iterable<PostResponseDTO> FindAllByIds(Iterable<Long> ids){
        Iterable<Post> posts = postRepository.findAllById(ids);
        ArrayList<PostResponseDTO> res = new ArrayList<PostResponseDTO>();
        for (Post post: posts) {
            Long param = post.getUser_id();
            ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://user-service/api/users/{id}",
                            UserDTO.class, param);
            UserDTO userDTO = responseEntity.getBody();
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setImage_path(post.getImage_path());
            postDTO.setTitle(post.getTitle());
            postDTO.setUser_id(post.getUser_id());
            postDTO.setDescription(post.getDescription());
            postDTO.setHashtagNames(post.getHashtags().stream().map(hashtag -> {
                return hashtag.getName();
            } ).collect(Collectors.toSet()));
            PostResponseDTO postResponseDTO = new PostResponseDTO();
            postResponseDTO.setPostDTO(postDTO);
            postResponseDTO.setUserDTO(userDTO);
            res.add(postResponseDTO);
        }
        Iterable<PostResponseDTO>  response = res;
        return  response;
    }
    @Override
    public Post FindById( Long id){
        Post post = postRepository.findById(id).orElse(null);

        return post;
    }

    @Override
    public Iterable<Post> FindByUserId(Long id) {
        Iterable<Post> posts = postRepository.findByUserId(id);
        return posts;
    }

    @Override
    public Iterable<PostResponseDTO> filterPosts(FilterDTO filterDTO) {
        Iterable<Post> posts = postRepository.findAll();
        ArrayList<PostResponseDTO> res = new ArrayList<PostResponseDTO>();
        String[] searchWords = filterDTO.getSearch().toLowerCase().split(" ");
        for (Post post: posts) {
            //filter posts
            Boolean contains = false;
            for (String word : searchWords) {
                if (post.getTitle().toLowerCase().contains(word.toLowerCase())) {
                    contains = true;
                    break;
                }
                if (post.getHashtags().stream().anyMatch(h -> h.getName().toLowerCase().equals(word.toLowerCase()))){
                    contains = true;
                    break;
                }
            }
            if(contains == true) {

                Long param = post.getUser_id();
                ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://user-service/api/users/{id}",
                        UserDTO.class, param);
                UserDTO userDTO = responseEntity.getBody();
                PostDTO postDTO = new PostDTO();
                postDTO.setId(post.getId());
                postDTO.setImage_path(post.getImage_path());
                postDTO.setTitle(post.getTitle());
                postDTO.setUser_id(post.getUser_id());
                postDTO.setDescription(post.getDescription());
                postDTO.setHashtagNames(post.getHashtags().stream().map(hashtag -> {
                    return hashtag.getName();
                }).collect(Collectors.toSet()));
                PostResponseDTO postResponseDTO = new PostResponseDTO();
                postResponseDTO.setPostDTO(postDTO);
                postResponseDTO.setUserDTO(userDTO);
                res.add(postResponseDTO);
            }
        }


        Iterable<PostResponseDTO>  response = res;
        return  response;
    }
    @Override
    public Boolean Delete (Long id){
        Post post = postRepository.findById(id).orElse(null);
        if(post == null) {
            throw new PinwayError("Not found Post with id = " + id);
        }
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public Post Update(PostDTO postDTO, Iterable<Hashtag> hashtags){
        Post post = postRepository.findById(postDTO.getId()).orElse(null);
        if(post == null){
            throw new PinwayError("Not found Post with id = " + postDTO.getId());
        }
        post.setTitle(postDTO.getTitle());
        post.setImage_path(postDTO.getImage_path());
        post.setDescription(postDTO.getDescription());
        post.setHashtags((Set<Hashtag>) hashtags);
        postRepository.save(post);
        return  post;
    }
}