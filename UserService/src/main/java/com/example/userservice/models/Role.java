package com.example.userservice.models;


import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleID;
    @NotBlank(message = "Name of role is mandatory")
    private String name;


    public Role(String name) {
        this.name = name;
    }

    public Role() {

    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

