package com.example.notificationservice.dto;

import jakarta.validation.constraints.NotNull;

public class NotificationOpenOnlyDTO {

    private Integer id;

    @NotNull(message = "Field open must not be null")
    private Boolean open;

    public NotificationOpenOnlyDTO() {
    }

    public NotificationOpenOnlyDTO(Integer id, Boolean open) {
        this.id = id;
        this.open = open;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
