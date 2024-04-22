package com.dhfinalproject.productservice.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Document(value = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private String productID;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageURL;
    @Field("category")
    @DocumentReference(lookup = "{ 'categoryName' : ?#{category_name} }")
    private Category category;

}
