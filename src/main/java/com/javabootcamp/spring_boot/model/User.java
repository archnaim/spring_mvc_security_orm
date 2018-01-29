package com.javabootcamp.spring_boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String username;
    private String password;
    private String email;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(){}

    public User(String username, String password, String email) {
//        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
