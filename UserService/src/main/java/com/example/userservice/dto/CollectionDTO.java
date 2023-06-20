package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CollectionDTO {

    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name must contain less than 20 characters")
    private String name;

    @NotNull
    private CollectionVisibilityTypeDTO collectionVisibilityTypeDTO;

    public CollectionDTO(Integer id, String name, CollectionVisibilityTypeDTO collectionVisibilityTypeDTO) {
        this.id = id;
        this.name = name;
        this.collectionVisibilityTypeDTO = collectionVisibilityTypeDTO;
    }


    public CollectionDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionVisibilityTypeDTO getCollectionVisibilityTypeDTO() {
        return collectionVisibilityTypeDTO;
    }

    public void setCollectionVisibilityTypeDTO(CollectionVisibilityTypeDTO collectionVisibilityTypeDTO) {
        this.collectionVisibilityTypeDTO = collectionVisibilityTypeDTO;
    }
}
