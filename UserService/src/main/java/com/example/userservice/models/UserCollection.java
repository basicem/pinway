package com.example.userservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class UserCollection {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    private Integer collectionId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public UserCollection(Integer id, Integer collectionId, User user) {
        Id = id;
        this.collectionId = collectionId;
        this.user = user;
    }

    public UserCollection(Integer collectionId, User user) {
        this.collectionId = collectionId;
        this.user = user;
    }

    public UserCollection() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
