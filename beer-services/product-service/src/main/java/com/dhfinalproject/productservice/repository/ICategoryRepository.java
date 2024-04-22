package com.dhfinalproject.productservice.repository;

import com.dhfinalproject.productservice.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoryRepository extends MongoRepository<Category,String> {
    Category findByCategoryName(String categoryName);

}
