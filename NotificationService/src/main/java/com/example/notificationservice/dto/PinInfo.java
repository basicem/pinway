package com.example.notificationservice.dto;

public class PinInfo {

    private Long id;

    private Integer collectionId;
    private String message;

    private Integer userId;

    private String username;

    private Integer actionUserId;

    public PinInfo() {
    }

    public PinInfo(Long id, Integer collectionId, String message, Integer userId, String username, Integer actionUserId) {
        this.id = id;
        this.collectionId = collectionId;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.actionUserId = actionUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
