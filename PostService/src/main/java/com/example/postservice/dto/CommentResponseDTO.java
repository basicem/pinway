package com.example.postservice.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDTO {
    public UserDTO userDTO;
    public CommentDTO commentDTO;
}
