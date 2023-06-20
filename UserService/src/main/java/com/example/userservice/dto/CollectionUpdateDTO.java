package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CollectionUpdateDTO {

    @NotBlank(message = "Type is mandatory")
    @Size(max = 20, message = "Type must contain less than 20 characters")
    @Pattern(regexp = "^[A-Z]*$", message = "Type must contain only uppercase letters")
    private String type;

    public CollectionUpdateDTO(String type) {
        this.type = type;
    }

    public CollectionUpdateDTO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
