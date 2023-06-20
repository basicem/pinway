package com.example.userservice.dto;

public class CollectionUserDTO {

    private CollectionDTO collectionDTO;

    public CollectionUserDTO(CollectionDTO collectionDTO) {
        this.collectionDTO = collectionDTO;
    }

    public CollectionDTO getCollectionDTO() {
        return collectionDTO;
    }

    public void setCollectionDTO(CollectionDTO collectionDTO) {
        this.collectionDTO = collectionDTO;
    }
}
