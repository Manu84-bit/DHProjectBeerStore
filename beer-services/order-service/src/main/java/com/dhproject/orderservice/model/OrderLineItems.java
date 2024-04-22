package com.dhproject.orderservice.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "t_orderlineitems")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;
    private String stockCode;
    private BigDecimal price;
    private Integer quantity;
}
