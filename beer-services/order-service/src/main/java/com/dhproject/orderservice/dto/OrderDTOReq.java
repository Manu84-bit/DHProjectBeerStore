package com.dhproject.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOReq {
    private String userId;
    private List<OrderLineItemsDTO> orderLineItemsDTOList;
}
