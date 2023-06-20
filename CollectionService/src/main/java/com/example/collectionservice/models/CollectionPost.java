package com.example.collectionservice.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
public class CollectionPost {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer Id;

    private Long postId;

    @NotNull
    @ManyToOne
    @JoinColumn(name="collectionId")
    private Collection collection;

    public CollectionPost(Long postId, Collection collection) {
        this.postId = postId;
        this.collection = collection;
    }

    public CollectionPost() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
