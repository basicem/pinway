package com.example.userservice.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.annotation.CreatedDate;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;
import java.util.*;

@Entity // This tells Hibernate to make a table out of this class
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String guid;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name must contain less than 20 characters")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Size(max = 20, message = "Surname must contain less than 20 characters")
    private String surname;

    @NotBlank(message = "Username is mandatory")
    @Size(max = 20, message = "Username must contain less than 20 characters")
    private String username;

    @Email(regexp = ".+[@].+[\\.].+")
    @Email(message="Please provide a valid email address")
    private String email;

    @NotEmpty
    //@Pattern(regexp = "^(0$|[^0]\\d{0,19}$)", flags = Pattern.Flag.MULTILINE)
    //@Size(min = 5, max = 20, message = "Password must contain minimum 5 characters")
    private String password;


    @CreatedDate
    private LocalDate createdAt;

    private String image_path;

    @NotNull
    @ManyToOne
    @JoinColumn(name="typeId")
    private UserVisibilityType userVisibilityType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    @JsonManagedReference
    private List<User> following;

    @JsonIgnoreProperties("followers")
    @ManyToMany(mappedBy = "following")
    @JsonBackReference
    private List<User> followers;

    private Integer numOfFollowing;
    private Integer numOfFollowers;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<UserCollection> userCollections;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private List<Role> roles = new ArrayList<>();

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

    public UserVisibilityType getUserVisibilityType() {
        return userVisibilityType;
    }

    public void setUserVisibilityType(UserVisibilityType userVisibilityType) {
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

    public List<UserCollection> getUserCollections() {
        return userCollections;
    }

    public void setUserCollections(List<UserCollection> userCollections) {
        this.userCollections = userCollections;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<String> getRolesByName() {
        List<String> roleNames = new ArrayList<>();
        for (Role r: getRoles()) {
            roleNames.add(r.getName());
        }
        return roleNames;
    }


    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

}