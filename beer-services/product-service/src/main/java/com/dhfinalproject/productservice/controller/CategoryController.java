package com.dhfinalproject.productservice.controller;

import com.dhfinalproject.productservice.dto.CategoryDTO;
import com.dhfinalproject.productservice.dto.CategoryDTORes;
import com.dhfinalproject.productservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory (@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTORes> getAllCategories(){

        return categoryService.getAllCategories();
    }

}
