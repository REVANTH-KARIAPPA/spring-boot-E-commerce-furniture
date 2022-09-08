package com.furniture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    private int orderTotal;
    private boolean status=false;

    @OneToOne
    private Payment payment;

    @ManyToMany
    @JoinTable(name ="ordered_products",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "product_productId"))
    private Set<Product> products;
    @JsonIgnore
    @ManyToOne
    private User users;

    public void setProducts(Set<Product> products) {
        this.products = products;

        for(Product e: products){
            setOrderTotal(getOrderTotal() + e.getPrize()) ;
        }

    }

    public void putProduct(Product product) {
        orderTotal=orderTotal+product.getPrize();
        products.add(product);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}