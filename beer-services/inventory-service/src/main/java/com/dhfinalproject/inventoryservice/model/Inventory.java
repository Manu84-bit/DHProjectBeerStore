package com.dhfinalproject.inventoryservice.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "t_inventory")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String stockCode;
    private Integer stockQuantity;
}
