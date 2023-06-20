package com.example.userservice.dto;

public class UserCollectionCreateDTO {

    private Integer collectionId;

    public UserCollectionCreateDTO() {
    }

    public UserCollectionCreateDTO(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }
}
