package com.example.collectionservice.infrastructure;

import com.example.collectionservice.dto.CollectionPostCreateDTO;
import com.example.collectionservice.dto.PostDTO;
import com.example.collectionservice.dto.PostResponseDTO;
import com.example.collectionservice.dto.PostUserDTO;
import com.example.collectionservice.exception.PinwayError;
import com.example.collectionservice.models.Collection;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean DoesExist(Long postId)  {
        ResponseEntity<PostUserDTO[]> responseEntity = restTemplate.getForEntity("http://post-service/api/post/findByIds?Ids={ids}", PostUserDTO[].class, postId);
        PostUserDTO[] postUserDTOs = responseEntity.getBody();

        if(postUserDTOs.length != 1)
            return false;

        PostUserDTO postUserDTO = postUserDTOs[0];
        PostDTO postDTO = postUserDTO.getPostDTO();
        if(postDTO.getId() != postId)
            return false;

        return true;
    }

    public PostResponseDTO GetPost(Long postId)  {
        ResponseEntity<PostResponseDTO[]> responseEntity = restTemplate.getForEntity("http://post-service/api/post/findByIds?Ids={ids}", PostResponseDTO[].class, postId);
        PostResponseDTO[] postUserDTOs = responseEntity.getBody();

        if(postUserDTOs.length != 1)
            return null;

        PostResponseDTO postUserDTO = postUserDTOs[0];

        return postUserDTO;
    }

    public List<PostDTO> GetAllPostsForCollection(Set<Long> ids) {
        String listOfIds = ids.stream().map(Object::toString).collect(Collectors.joining(","));
        ResponseEntity<PostUserDTO[]> responseEntity = restTemplate.getForEntity("http://post-service/api/post/findByIds?Ids={ids}", PostUserDTO[].class, listOfIds);
        PostUserDTO[] postUserDTOs = responseEntity.getBody();

        List<PostDTO> postDTOS = new ArrayList<>();

        for(PostUserDTO postUserDTO: postUserDTOs) {
            postDTOS.add(postUserDTO.getPostDTO());
        }

        return postDTOS;
    }

}
