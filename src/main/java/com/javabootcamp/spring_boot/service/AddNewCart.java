package com.javabootcamp.spring_boot.service;

import com.javabootcamp.spring_boot.model.Cart;
import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.repository.CartRepository;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import com.javabootcamp.spring_boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddNewCart {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public void add(String username, Long productId)
    {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(new Product());

        Cart cart = new Cart(user,product);

        cartRepository.save(cart);
    }




}
