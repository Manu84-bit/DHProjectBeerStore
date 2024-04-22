package com.dhfinalproject.productservice.controller;

import com.dhfinalproject.productservice.dto.ProductDTO;
import com.dhfinalproject.productservice.dto.ProductDTORes;
import com.dhfinalproject.productservice.model.Product;
import com.dhfinalproject.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDTO productDTO){
        productService.createProduct(productDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTORes> getAllProducts(){
       return productService.getAllProducts();
    }

}
