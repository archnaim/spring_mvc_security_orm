package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.model.Cart;
import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.repository.CartRepository;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import com.javabootcamp.spring_boot.service.AddNewCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class CartList {

    @Autowired
    private AddNewCart addNewCart;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/cart")
    public List<Product> getCart(Principal principal)
    {
        //TODO: get Cart
        List<Product> products = productRepository.findProductsInCartByUsername(principal.getName());
        return products;
    }

    @PostMapping("/cart")
    public void addCart(@RequestParam("productId") String productId, Principal principal,HttpServletResponse response) throws IOException {
        System.out.println("add to Cart");
        //TODO: prevent user adding item that already in cart
        if(cartRepository.getByUsernameAndProductId(principal.getName(),Long.valueOf(productId)).isPresent())
        {
            response.sendRedirect("/home?error");
        }
        else
        {
            addNewCart.add(principal.getName(),Long.valueOf(productId));
            response.sendRedirect("/home?purchased");
        }
    }



}
