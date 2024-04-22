package com.dhfinalproject.productservice.service;

import com.dhfinalproject.productservice.dto.CategoryDTO;
import com.dhfinalproject.productservice.dto.CategoryDTORes;
import com.dhfinalproject.productservice.dto.ProductDTORes;
import com.dhfinalproject.productservice.model.Category;
import com.dhfinalproject.productservice.model.Product;
import com.dhfinalproject.productservice.repository.ICategoryRepository;
import com.dhfinalproject.productservice.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryService {

    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;

    public CategoryService(ICategoryRepository categoryRepository, IProductRepository productRepository){
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void createCategory(CategoryDTO categoryDTO){
        Category category = Category.builder()
                .categoryName(categoryDTO.getCategoryName())
                .categoryDescription(categoryDTO.getCategoryDescription())
                .imgUrl(categoryDTO.getImgUrl())
                .build();
        category.setProductList(new ArrayList<Product>());
        categoryRepository.save(category);
        log.info("Category " + category.getCategoryID() + " was created");

    }

    public List<CategoryDTORes> getAllCategories (){
        List<Category> categories = categoryRepository.findAll();
        log.info("Product list has " + categories.size() + " items");
        return categories.stream().map(category -> mapToCategoryDTORes(category)).toList();
    }

    private CategoryDTORes mapToCategoryDTORes(Category category) {
        CategoryDTORes categoryDTORes = CategoryDTORes.builder()
                .categoryID(category.getCategoryID())
                .categoryName(category.getCategoryName())
                .imgUrl(category.getImgUrl())
                .categoryDescription(category.getCategoryDescription())
                .productsId(category.getProductList().stream()
                        .map(product->product.getProductID()).toList())
                .build();

        return categoryDTORes;
    }
}
