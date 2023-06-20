package com.example.userservice.dto;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CollectionResponseDTO {

    private CollectionDTO collectionDTO;

    private List<PostDTO> postDTO;

    public CollectionResponseDTO(CollectionDTO collectionDTO, List<PostDTO> postDTO) {
        this.collectionDTO = collectionDTO;
        this.postDTO = postDTO;
    }

    public CollectionResponseDTO() {
    }

    public CollectionDTO getCollectionDTO() {
        return collectionDTO;
    }

    public void setCollectionDTO(CollectionDTO collectionDTO) {
        this.collectionDTO = collectionDTO;
    }

    public List<PostDTO> getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(List<PostDTO> postDTO) {
        this.postDTO = postDTO;
    }
}
