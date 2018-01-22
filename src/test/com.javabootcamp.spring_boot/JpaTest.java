package com.javabootcamp.spring_boot;

import com.javabootcamp.spring_boot.model.Role;
import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.model.UserRole;
import com.javabootcamp.spring_boot.repository.RoleRepository;
import com.javabootcamp.spring_boot.repository.UserRepository;
import com.javabootcamp.spring_boot.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

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
}
