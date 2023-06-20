package com.example.postservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer hash_counter;
    private Integer pin_counter;

    @JsonIgnore
    @ManyToMany(mappedBy = "hashtags")
    Set<Post> posts;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHash_counter() {
        return hash_counter;
    }

    public void setHash_counter(Integer hash_counter) {
        this.hash_counter = hash_counter;
    }

    public Integer getPin_counter() {
        return pin_counter;
    }

    public void setPin_counter(Integer pin_counter) {
        this.pin_counter = pin_counter;
    }
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
