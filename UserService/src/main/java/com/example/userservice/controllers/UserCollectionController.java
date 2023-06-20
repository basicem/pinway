package com.example.userservice.controllers;


import com.example.userservice.dto.*;
import com.example.userservice.models.User;
import com.example.userservice.models.UserVisibilityType;
import com.example.userservice.services.UserCollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api")
public class UserCollectionController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserCollectionService userCollectionService;

    @PostMapping(path="/users/{id}/collection") // Map ONLY POST Requests
    public @ResponseBody ResponseEntity AddUserCollection (@PathVariable("id") Integer id, @Valid @RequestBody UserCollectionCreateDTO requestBody) {
        UserDTO user = userCollectionService.AddCollection(id, requestBody);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping(path = "/users/{id}/collection")
    public  @ResponseBody ResponseEntity GetAllCollectionsForUser(@PathVariable("id") Integer id) {
        UserResponseDTO userResponseDTO = userCollectionService.GetAllCollectionsForUser(id);
        return ResponseEntity.status(200).body(userResponseDTO);
    }

    // userId       - onaj od kojeg se trazi
    // actionUserId - onaj koji trazi
    // mora post zbog bodya
    @PostMapping(path = "/users/collection")
    public  @ResponseBody ResponseEntity GetSharedAndPublicCollectionsForUserFromUser(@Valid @RequestBody CollectionRequestDTO collectionRequestDTO) {
        Iterable<CollectionResponseDTO> collectionDTOS = userCollectionService.GetSharedAndPublicCollectionsForUserFromUser(collectionRequestDTO.getActionUserId(), collectionRequestDTO.getUserId());
        return ResponseEntity.status(200).body(collectionDTOS);
    }


}