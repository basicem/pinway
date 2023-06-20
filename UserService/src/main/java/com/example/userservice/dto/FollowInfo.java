package com.example.userservice.dto;

public class FollowInfo {

    // onom kome ide obavijest da ga je neko zapratio
    private Integer userId;
    private String message;
    //onaj koji je zapratio
    private Integer actionUserId;

    private String username;

    public FollowInfo(Integer userId, String message, Integer actionUserId, String username) {
        this.userId = userId;
        this.message = message;
        this.actionUserId = actionUserId;
        this.username = username;
    }

    public FollowInfo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
