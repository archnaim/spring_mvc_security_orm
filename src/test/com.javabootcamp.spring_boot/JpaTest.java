package com.javabootcamp.spring_boot;

import com.javabootcamp.spring_boot.model.*;
import com.javabootcamp.spring_boot.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testUserRepo()
    {
        User user = new User("user","password","test@test.com");
        userRepository.save(user);

        User result = userRepository.findByUsername("user");

        Assert.assertEquals("password", result.getPassword());

    }

    @Test
    public void testUserRoleRepo()
    {
        Role role = new Role("ROLE_ADMIN");
        roleRepository.save(role);

        User user = new User("user","password","test@test.com");
        userRepository.save(user);

        UserRole userRole = new UserRole(user,role);
        userRoleRepository.save(userRole);

        Assert.assertEquals("ROLE_ADMIN",userRoleRepository.findRoleByUsername("user"));

    }

    @Test
    public void testProductRepo()
    {
        Product product = new Product("Product1",100,"desc1",10,"type1");
        productRepository.save(product);

        User user = userRepository.findByUsername("admin");

        cartRepository.save(new Cart(user,product));

        List<Product> products = productRepository.findProductsInCartByUsername("admin");

        Assert.assertEquals(product,products.get(0));

    }


}
