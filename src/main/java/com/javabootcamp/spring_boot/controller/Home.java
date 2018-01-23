package com.javabootcamp.spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class Home {

    @RequestMapping("/")
    public String getIndex()
    {
        return "index";
    }

    @RequestMapping("/home")
    public String getHomePage()
    {
        return "index";
    }

}
