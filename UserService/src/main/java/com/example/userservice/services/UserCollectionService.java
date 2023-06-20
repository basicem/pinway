package com.example.userservice.services;

import com.example.userservice.dto.*;
import com.example.userservice.models.User;

public interface UserCollectionService {

    UserDTO AddCollection(Integer id, UserCollectionCreateDTO userCollectionCreateDTO);

    UserResponseDTO GetAllCollectionsForUser(Integer id);

    Iterable<CollectionResponseDTO> GetSharedAndPublicCollectionsForUserFromUser(Integer actionUserId, Integer userId);
}
