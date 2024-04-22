package com.dhfinalproject.productservice.dto;

import com.dhfinalproject.productservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTORes {
    private String categoryID;
    private String categoryName;
    private String categoryDescription;
    private String imgUrl;
    List<String> productsId;
}
