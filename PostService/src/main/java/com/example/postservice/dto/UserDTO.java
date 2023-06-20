package com.example.postservice.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        private Integer id;
        private String guid;
        private String name;
        private String surname;
        private String username;
        private String email;
        private String password;
        private LocalDate createdAt;
        private UserVisibilityTypeDTO userVisibilityType;
        private Integer numOfFollowing;
        private Integer numOfFollowers;
}
