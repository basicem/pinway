package com.example.postservice.dto;

public class LikeInfo {

    private Integer id;
    private String message;

    private Long postId;

    private Integer userId;

    private String username;

    private Integer actionUserId;


    public LikeInfo() {
    }

    public LikeInfo(Integer id, String message, Long postId, Integer userId, String username, Integer actionUserId) {
        this.id = id;
        this.message = message;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.actionUserId = actionUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(Integer actionUserId) {
        this.actionUserId = actionUserId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}


