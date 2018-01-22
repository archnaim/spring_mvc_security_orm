package com.javabootcamp.spring_boot.model;

import javax.persistence.*;

@Entity
@Table(name = "user_role",
uniqueConstraints = @UniqueConstraint(name = "user_role_uk",columnNames = {"userId","roleId"}))
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
