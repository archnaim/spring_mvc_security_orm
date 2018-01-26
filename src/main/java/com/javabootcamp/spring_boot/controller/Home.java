package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.Constant;
import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class Home {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    public String getIndex()
    {
        return "redirect:/home";
    }


    @RequestMapping("/home")
    public String getHomePage(@RequestParam(value = "page",defaultValue = "0",required = false) Integer page,Model model)
    {
        List<String> categories = productRepository.findDistinctCategories();
        Iterable<Product> products = productRepository.findAll(new PageRequest(page, Constant.MAX_ITEMS_PER_PAGE));

        model.addAttribute("products",products);
        model.addAttribute("categories",categories);

        return "index";
    }

    @RequestMapping(value = "/home",params = {"type"})
    public String getHomePage(@RequestParam(value = "type") String type,
                              @RequestParam(value = "page",defaultValue = "0",required = false) Integer page,
                              Model model)
    {
        List<String> categories = productRepository.findDistinctCategories();
        Iterable<Product> products = productRepository.findByType(type,new PageRequest(page, Constant.MAX_ITEMS_PER_PAGE));

        model.addAttribute("products",products);
        model.addAttribute("categories",categories);

        return "index";
    }



}
