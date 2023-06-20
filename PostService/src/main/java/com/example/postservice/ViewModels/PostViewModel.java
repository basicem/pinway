package com.example.postservice.ViewModels;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class PostViewModel {
    private Long id;

    private String title;

    private String description;

    private String image_path;

    private Long user_id;

    private Integer pin_counter;

    private LocalDateTime created_at;
}
