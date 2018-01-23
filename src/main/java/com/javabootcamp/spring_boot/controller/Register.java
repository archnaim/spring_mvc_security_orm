package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.model.Role;
import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.model.UserRole;
import com.javabootcamp.spring_boot.repository.RoleRepository;
import com.javabootcamp.spring_boot.repository.UserRepository;
import com.javabootcamp.spring_boot.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Register {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/register")
    public String getRegisterPage()
    {
//        model.addAttribute("message", message);
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(@RequestParam("username") String username, @RequestParam("password") String passsword,
                                   @RequestParam("email") String email, Model model) {

        User user = userRepository.findByUsername(username);

        if(user != null)
        {
            model.addAttribute("error","username is already registered");
            return "redirect:/register?error";
        }
        else {
            user = new User(username,passsword,email);
            userRepository.save(user);

            Role role = roleRepository.findByRoleName("ROLE_USER");

            UserRole userRole = new UserRole(user,role);
            userRoleRepository.save(userRole);

            return "redirect:/login?registered";
        }





    }

}
