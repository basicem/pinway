package com.example.userservice.dto;

public class CollectionRequestDTO {

    private Integer userId;

    private Integer actionUserId;

    public CollectionRequestDTO(Integer userId, Integer actionUserId) {
        this.userId = userId;
        this.actionUserId = actionUserId;
    }

    public CollectionRequestDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(Integer actionUserId) {
        this.actionUserId = actionUserId;
    }
}
