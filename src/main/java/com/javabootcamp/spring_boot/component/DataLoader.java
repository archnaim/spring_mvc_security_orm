package com.javabootcamp.spring_boot.component;

import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.model.Role;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import com.javabootcamp.spring_boot.repository.RoleRepository;
import com.javabootcamp.spring_boot.service.RegisterNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RegisterNewUser registerNewUser;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("INSERT user admin to database");

//        Role roleUser = new Role("ROLE_USER");
//        Role roleAdmin= new Role("ROLE_ADMIN");
//
//        roleRepository.save(roleUser);
//        roleRepository.save(roleAdmin);
//
//        registerNewUser.createNewUser("admin","admin","naimaryudya@gmail.com","ROLE_ADMIN");
//
////        List<Product> products = Arrays.asList(
////                new Product(),
////                new Product());
//
////        productRepository.save(products);
    }
}
