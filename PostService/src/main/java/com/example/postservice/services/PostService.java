package com.example.postservice.services;


import com.example.postservice.dto.FilterDTO;
import com.example.postservice.dto.PostDTO;
import com.example.postservice.dto.PostResponseDTO;
import com.example.postservice.models.Hashtag;
import com.example.postservice.models.Post;

public interface PostService {

     Post Create(Post post);
     Iterable<PostResponseDTO> List();
     Iterable<PostResponseDTO> FindAllByIds(Iterable<Long> ids);
     Post FindById( Long id);
     Iterable<PostResponseDTO> filterPosts(FilterDTO filterDTO);
     Iterable<Post> FindByUserId(Long id);
     Boolean Delete (Long id);
     Post Update(PostDTO postDTO, Iterable<Hashtag> hashtags);
}
