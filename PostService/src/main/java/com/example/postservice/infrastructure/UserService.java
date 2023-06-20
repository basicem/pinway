package com.example.postservice.infrastructure;

import com.example.postservice.dto.UserDTO;
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