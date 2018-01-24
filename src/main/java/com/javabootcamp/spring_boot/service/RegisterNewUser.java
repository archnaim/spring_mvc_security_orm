package com.javabootcamp.spring_boot.service;

import com.javabootcamp.spring_boot.model.Role;
import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.model.UserRole;
import com.javabootcamp.spring_boot.repository.RoleRepository;
import com.javabootcamp.spring_boot.repository.UserRepository;
import com.javabootcamp.spring_boot.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterNewUser{

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void createNewUser(String username, String password, String email, String rolename) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User(username,encoder.encode(password),email);

        System.out.println(user.toString());

        this.userRepository.save(user);

        Role role = this.roleRepository.findByRoleName(rolename);

        UserRole userRole = new UserRole(user,role);
        this.userRoleRepository.save(userRole);
    }
}
