package com.dhfinalproject.productservice.repository;

import com.dhfinalproject.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IProductRepository  extends MongoRepository<Product, String> {


}
