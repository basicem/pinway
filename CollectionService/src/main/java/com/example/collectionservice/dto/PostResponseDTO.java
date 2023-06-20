package com.example.collectionservice.dto;

public class PostResponseDTO {

    public  PostDTO postDTO;
    public  UserDTO userDTO;

    public PostResponseDTO(PostDTO postDTO, UserDTO userDTO) {
        this.postDTO = postDTO;
        this.userDTO = userDTO;
    }

    public PostResponseDTO() {
    }

    public PostDTO getPostDTO() {
        return postDTO;
    }

    public void setPostDTO(PostDTO postDTO) {
        this.postDTO = postDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
