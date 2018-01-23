package com.javabootcamp.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Admin {

    @RequestMapping("/admin")
    public String getAdminPage()
    {
        return "admin";
    }

    //TODO:Admin page

}
