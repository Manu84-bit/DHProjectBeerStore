package com.dhfinalproject.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.util.List;

@Document(value = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    private String categoryID;
    private String categoryName;
    private String categoryDescription;
    private String imgUrl;
    @DocumentReference(lazy = false)
    List<Product> productList;
}
