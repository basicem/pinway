package com.example.userservice.dto;

import java.util.List;

public class UserResponseDTO {
    private UserDTO userDTO;
    private List<CollectionResponseDTO> collectionDTOS;

    public UserResponseDTO(UserDTO userDTO, List<CollectionResponseDTO> collectionDTOS) {
        this.userDTO = userDTO;
        this.collectionDTOS = collectionDTOS;
    }

    public UserResponseDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CollectionResponseDTO> getCollectionDTOS() {
        return collectionDTOS;
    }

    public void setCollectionDTOS(List<CollectionResponseDTO> collectionDTOS) {
        this.collectionDTOS = collectionDTOS;
    }
}
