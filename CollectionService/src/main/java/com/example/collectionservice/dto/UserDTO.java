package com.example.collectionservice.dto;

import java.time.LocalDate;

public class UserDTO {
    private Integer id;
    private String guid;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private LocalDate createdAt;
    private UserVisibilityTypeDTO userVisibilityType;
    private Integer numOfFollowing;
    private Integer numOfFollowers;

    public UserDTO(Integer id, String guid, String name, String surname, String username, String email, String password, LocalDate createdAt, UserVisibilityTypeDTO userVisibilityType, Integer numOfFollowing, Integer numOfFollowers) {
        this.id = id;
        this.guid = guid;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.userVisibilityType = userVisibilityType;
        this.numOfFollowing = numOfFollowing;
        this.numOfFollowers = numOfFollowers;
    }

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public UserVisibilityTypeDTO getUserVisibilityType() {
        return userVisibilityType;
    }

    public void setUserVisibilityType(UserVisibilityTypeDTO userVisibilityType) {
        this.userVisibilityType = userVisibilityType;
    }

    public Integer getNumOfFollowing() {
        return numOfFollowing;
    }

    public void setNumOfFollowing(Integer numOfFollowing) {
        this.numOfFollowing = numOfFollowing;
    }

    public Integer getNumOfFollowers() {
        return numOfFollowers;
    }

    public void setNumOfFollowers(Integer numOfFollowers) {
        this.numOfFollowers = numOfFollowers;
    }
}
