package com.example.collectionservice.services;

import com.example.collectionservice.dto.CollectionPostCreateDTO;
import com.example.collectionservice.dto.CollectionResponseDTO;
import com.example.collectionservice.models.Collection;

public interface CollectionPostService {

    Collection AddPost(Integer id, CollectionPostCreateDTO collectionPostCreateDTO);


    CollectionResponseDTO GetAllPostsForCollection(Integer id);

    void RemovePost(Integer collectionId, Long postId);

    Iterable<CollectionResponseDTO> FindAllByUserId(Integer id);

    Iterable<CollectionResponseDTO> FindPublicCollectionsForUser(Integer id);
}
