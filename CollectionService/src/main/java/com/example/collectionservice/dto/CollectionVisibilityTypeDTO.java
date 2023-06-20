package com.example.collectionservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CollectionVisibilityTypeDTO {

    private Integer id;

    @NotBlank(message = "Type is mandatory")
    @Size(max = 20, message = "Type must contain less than 20 characters")
    @Pattern(regexp = "^[A-Z]*$", message = "Type must contain only uppercase letters")
    private String type;

    public CollectionVisibilityTypeDTO(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public CollectionVisibilityTypeDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
