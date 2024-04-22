package com.dhfinalproject.productservice.service;

import com.dhfinalproject.productservice.dto.ProductDTO;
import com.dhfinalproject.productservice.dto.ProductDTORes;
import com.dhfinalproject.productservice.model.Category;
import com.dhfinalproject.productservice.model.Product;
import com.dhfinalproject.productservice.repository.ICategoryRepository;
import com.dhfinalproject.productservice.repository.IProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public ProductService(IProductRepository productRepository, ICategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public void createProduct (ProductDTO productDTO){
        Product product = Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imageURL(productDTO.getImageURL())
                .category(categoryRepository.findByCategoryName(productDTO.getCategoryName()))
                .build();
        productRepository.save(product);

        Category category = categoryRepository.findByCategoryName(productDTO.getCategoryName());
        category.getProductList().add(product);
        categoryRepository.save(category);

        log.info("Product " + product.getProductID() + " of category "+
                product.getCategory().getCategoryName() + " was created");
    }

    public List<ProductDTORes> getAllProducts (){
        List<Product> products = productRepository.findAll();
        log.info("Product list has " + products.size() + " items");
        return products.stream().map(product -> mapToProductDTORes(product)).toList();

    }

    private ProductDTORes mapToProductDTORes(Product product) {
        return ProductDTORes.builder()
                .productID(product.getProductID())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageURL(product.getImageURL())
                .categoryName(product.getCategory().getCategoryName())
                .build();
    }
}
