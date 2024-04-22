package com.dhproject.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO {
    private Long orderLineId;
    private String stockCode;
    private BigDecimal price;
    private Integer quantity;
}
