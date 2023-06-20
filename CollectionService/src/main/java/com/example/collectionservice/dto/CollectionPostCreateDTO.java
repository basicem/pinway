package com.example.collectionservice.dto;

public class CollectionPostCreateDTO {
    private Long postId;

    private Integer actionUserId;

    public CollectionPostCreateDTO() {
    }

    public CollectionPostCreateDTO(Long postId, Integer actionUserId) {
        this.postId = postId;
        this.actionUserId = actionUserId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getActionUserId() {
        return actionUserId;
    }

    public void setActionUserId(Integer actionUserId) {
        this.actionUserId = actionUserId;
    }
}
