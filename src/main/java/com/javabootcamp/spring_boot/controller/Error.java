package com.javabootcamp.spring_boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Error {

    @RequestMapping("/403")
    public String accessDenied()
    {
        return "403";
    }
}
