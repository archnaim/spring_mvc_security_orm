package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.model.User;
import com.javabootcamp.spring_boot.repository.UserRepository;
import com.javabootcamp.spring_boot.service.RegisterNewUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Register {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterNewUser registerNewUser;

    @GetMapping("/register")
    public String getRegisterPage()
    {
//        model.addAttribute("message", message);
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterPage(@RequestParam("username") String username, @RequestParam("password") String password,
                                   @RequestParam("email") String email, Model model) {

        User user = userRepository.findByUsername(username);

        if(user != null)
        {
            model.addAttribute("error","username is already registered");
            return "redirect:/register?error";
        }
        else {
            registerNewUser.createNewUser(username,password,email,"ROLE_USER");

            return "redirect:/login?registered";
        }





    }

}
