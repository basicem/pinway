package com.example.collectionservice.infrastructure;

import com.example.collectionservice.dto.PostDTO;
import com.example.collectionservice.dto.PostResponseDTO;
import com.example.collectionservice.dto.PostUserDTO;
import com.example.collectionservice.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    public UserDTO GetUser(Integer id)  {
        ResponseEntity<UserDTO> responseEntity = restTemplate.getForEntity("http://user-service/api/users/{id}", UserDTO.class, id);
        return responseEntity.getBody();
    }
}
