package com.javabootcamp.spring_boot.controller;

import com.javabootcamp.spring_boot.model.Cart;
import com.javabootcamp.spring_boot.model.Product;
import com.javabootcamp.spring_boot.repository.CartRepository;
import com.javabootcamp.spring_boot.repository.ProductRepository;
import com.javabootcamp.spring_boot.service.AddNewCart;
import org.omg.CORBA.BAD_PARAM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
//@RestController
public class CartList {

    @Autowired
    private AddNewCart addNewCart;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

//    @GetMapping("/cart")
//    public List<Cart> getCart(Principal principal, Model model)
//    {
//        //TODO: get Cart
//        List<Cart> carts = cartRepository.getByUsername(principal.getName());
//        return carts;
//    }

    @GetMapping("/cart")
    public String getCart(Principal principal, Model model)
    {
        //TODO: get Cart
        List<Cart> carts = cartRepository.getByUsername(principal.getName());

        Float total = carts.stream().map(x->x.getProduct().getPrice()).reduce((x,y)->x+y).orElse((float)0);

        List<Float> prices = carts.stream().map(x->x.getProduct().getPrice()).collect(Collectors.toList());
        List<Integer> discounts = carts.stream().map(x->x.getProduct().getDiscount()).collect(Collectors.toList());
        List<Float> saves = new ArrayList<>();

        for(int i=0;i<prices.size();i++)
        {
            saves.add(prices.get(i)*discounts.get(i)/100);
        }
        Float save = saves.stream().reduce((x,y)->x+y).orElse((float)0);

        model.addAttribute("carts",carts);
        model.addAttribute("total",total);
        model.addAttribute("save",save);
        return "cart";
    }

    @PostMapping("/cart")
    public void addCart(@RequestParam("productId")  String productId, Principal principal,HttpServletResponse response) throws IOException {
        System.out.println("add to Cart");

        if(cartRepository.getByUsernameAndProductId(principal.getName(),Long.valueOf(productId)).isPresent())
        {
            response.sendRedirect("/home?error");
        }
        else
        {
            addNewCart.add(principal.getName(),Long.valueOf(productId));
            response.sendRedirect("/home?purchased");
        }
    }


    @PostMapping(value = "/delcart")
    public String delCart(@RequestParam("id") Long id,  Principal principal, Model model)
    {
        //TODO: get Cart
        System.out.println("Deleted Cart id ="+id);
        cartRepository.delete(cartRepository.findById(id).orElseThrow(BAD_PARAM::new));
        List<Cart> carts = cartRepository.getByUsername(principal.getName());
        model.addAttribute("carts",carts);

        return "redirect:/cart";
    }





}
