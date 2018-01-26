package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.Constant;
import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductList {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/product")
    public Iterable<Product> getAll(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page)
    {

        return productRepository.findAll(new PageRequest(page, Constant.MAX_ITEMS_PER_PAGE));
    }

    @RequestMapping(value = "/product",params = {"type"})
    public Page<Product> getByType(@RequestParam(value = "type") String type,
                                   @RequestParam(value = "page",defaultValue = "0",required = false) Integer page)
    {
        Page<Product> products = productRepository.findByType(type,new PageRequest(page,Constant.MAX_ITEMS_PER_PAGE));
        return products;
    }





}
