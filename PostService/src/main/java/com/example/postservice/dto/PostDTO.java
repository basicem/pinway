package com.example.postservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class PostDTO {
    private Long Id;
    @Size(min = 1, message = "Title must contain at least 1 character")
    @Size(max = 50, message = "Title must contain less than 50 characters")
    private String title;
    @Size(max = 500, message = "Title must contain less than 50 characters")
    private String description;

    @NotBlank(message = "Image is mandatory")
    private String image_path;

    private Long user_id;

    private Set<String> hashtagNames;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
    public Set<String> getHashtagNames() {
        return hashtagNames;
    }

    public void setHashtagNames(Set<String> hashtagNames) {
        this.hashtagNames = hashtagNames;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
