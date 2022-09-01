package com.furniture.service;

import com.furniture.Repository.CartRepository;
import com.furniture.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepo;

    public void addCart(Cart cart) {
        cartRepo.save(cart);
    }

    public  List<Cart> getAllCart() {
        return cartRepo.findAll();

    }

    public void deleteCart(int id) {
        cartRepo.deleteById(id);

    }
}
