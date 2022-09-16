package com.furniture.service;

import com.furniture.Repository.CartRepository;
import com.furniture.Repository.ProductRepository;
import com.furniture.Repository.UserRepository;
import com.furniture.exception.UserNameException;
import com.furniture.exception.UserNotFoundException;
import com.furniture.model.Cart;
import com.furniture.model.Product;
import com.furniture.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public void addCart(Cart cart){
        cartRepository.save(cart);
    }
    public List<Cart> getAllCart(){
        return cartRepository.findAll();
    }
    public  void deleteCart(int id){
        cartRepository.deleteById(id);
    }

    public void enrollCart(int cartId, int productId) {
        Cart cart= cartRepository.findById(cartId).get();
        Product product= productRepository.findById(productId).get();

        cart.addProducts(product);
        cartRepository.save(cart);

    }

    public void enrollUser(int cartId, long userId) {
        Cart cart= cartRepository.findById(cartId).get();


        User users = userRepository.findById(userId).get();

        users.setCart(cart);
        userRepository.save(users);


    }

    public void removeProduct(int cartId, int productId) {

        Cart cart=cartRepository.findById(cartId).get();
        Product product=productRepository.findById(productId).get();
        cart.setCartTotal(cart.getCartTotal()-product.getPrize());
        cart.getProducts().remove(product);
        cartRepository.save(cart);

    }

    public Set<Product> getProdutsInCart(long userId) {
        Optional<User> user= (userRepository.findById(userId));
        if(user.isPresent()) {
            User u=user.get();
            Cart c=u.getCart();
            int cId=c.getCartId();
            Set<Product> products=c.getProducts();
            return  products;
        }
        else {
            throw new UserNotFoundException("User "+userId+" is not  found");
        }
    }
}