package com.furniture.controller;

import com.furniture.model.Cart;
import com.furniture.model.Product;
import com.furniture.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3006)
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping("/all")
    public List<Cart> getAllCart() {

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

    @PutMapping("/{cartId}/product/{productId}")
    public String enrollCategory(@PathVariable int cartId, @PathVariable int productId){
        cartService.enrollCart(cartId,productId);
        return "product added to cart";

    }
    @PutMapping("/{cartId}/user/{userId}")
    public void enrollUser(@PathVariable int cartId, @PathVariable int userId){
        cartService.enrollUser(cartId,userId);

    }
    @PutMapping("/{cartId}/rep/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable int cartId, @PathVariable int productId){
        cartService.removeProduct(cartId,productId);
        return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);

    }
    @GetMapping("/{userId}")
    public Set<Product> getProductsInCart(@PathVariable long userId){

        return cartService.getProdutsInCart(userId);
    }

}