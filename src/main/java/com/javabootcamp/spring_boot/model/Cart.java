package com.javabootcamp.spring_boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "carts",
        uniqueConstraints = @UniqueConstraint(name = "user_product_fk",columnNames = {"userId","productId"}))
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    public Cart(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart( User user, Product product) {
//        this.id = id;
        this.user = user;
        this.product = product;
    }
}
