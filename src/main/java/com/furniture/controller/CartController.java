package com.furniture.controller;

import com.furniture.model.Cart;
import com.furniture.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cart")


public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/all")
    public List<Cart> getallcart(){
         return cartService.getAllCart();
    }
    @PostMapping("/create")
    public void addCategory(@RequestBody Cart cart){
        cartService.addCart(cart);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id){
        cartService.deleteCart(id);
    }

}

