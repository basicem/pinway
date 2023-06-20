package com.example.collectionservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Collection {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name must contain less than 20 characters")
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name="typeId")
    private CollectionVisibilityType collectionVisibilityType;

    @JsonIgnoreProperties("collection")
    @OneToMany(mappedBy = "collection")
    private List<CollectionPost> collectionPosts;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Min(value = 0, message = "Number of posts must be positive")
    @NotNull
    private Integer numOfPosts;


    // 0 - not deleted
    // 1 - deleted
    @NotNull
    private Boolean isDeleted;

    @NotNull
    private Long userId;

    public Collection() {
    }

    public Collection(Integer id, String name, CollectionVisibilityType collectionVisibilityType, List<CollectionPost> collectionPosts, LocalDate createdAt, Integer numOfPosts, Boolean deleted) {
        this.id = id;
        this.name = name;
        this.collectionVisibilityType = collectionVisibilityType;
        this.collectionPosts = collectionPosts;
        this.createdAt = LocalDate.now();
        this.numOfPosts = numOfPosts;
        this.isDeleted = deleted;
    }

    public Collection(Integer id, String name, CollectionVisibilityType collectionVisibilityType, List<CollectionPost> collectionPosts, LocalDate createdAt, Integer numOfPosts, Boolean isDeleted, Long userId) {
        this.id = id;
        this.name = name;
        this.collectionVisibilityType = collectionVisibilityType;
        this.collectionPosts = collectionPosts;
        this.createdAt = LocalDate.now();
        this.numOfPosts = numOfPosts;
        this.isDeleted = isDeleted;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionVisibilityType getCollectionVisibilityType() {
        return collectionVisibilityType;
    }

    public void setCollectionVisibilityType(CollectionVisibilityType collectionVisibilityType) {
        this.collectionVisibilityType = collectionVisibilityType;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getNumOfPosts() {
        return numOfPosts;
    }

    public void setNumOfPosts(Integer numOfPosts) {
        this.numOfPosts = numOfPosts;
    }

    public List<CollectionPost> getCollectionPosts() {
        return collectionPosts;
    }

    public void setCollectionPosts(List<CollectionPost> collectionPosts) {
        this.collectionPosts = collectionPosts;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        this.isDeleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}