package com.example.postservice.dto;

public class CommentInfo {

    private Long id;
    private String content;
    private String message;

    private Long postId;

    private Integer userId;

    private String username;

    private Integer actionUserId;

    public CommentInfo(Long id, String content, String message, Long postId, Integer userId, String username, Integer actionUserId) {
        this.id = id;
        this.content = content;
        this.message = message;
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.actionUserId = actionUserId;
    }

    public CommentInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
