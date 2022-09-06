package com.furniture.controller;

import com.furniture.model.Product;
import com.furniture.service.ProductService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/product")
@CrossOrigin(origins = "*", maxAge = 3006)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }
    @GetMapping("/{pid}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer pid)
    {
        Product product=productService.getProductById(pid);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('SELLER')")
    @PostMapping("/create")
    public void addProduct(@RequestBody Product product) {
        productService.createProduct(product);

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/approve/{id}")
    public void approveProduct(@PathVariable Integer id) {
        productService.approveProduct(id);

    }
    @PreAuthorize("hasAnyRole('ADMIN','SELLER','CUSTOMER')")
    @GetMapping("/approved")
    public List<Product> approvedProducts() {
        return productService.approvedProducts();

    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/not-approved")
    public List<Product> notApprovedProducts() {
       return  productService.notApprovedProduct();

    }





}