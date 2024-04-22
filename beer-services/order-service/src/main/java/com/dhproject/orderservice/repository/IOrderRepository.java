package com.dhproject.orderservice.repository;

import com.dhproject.orderservice.dto.OrderDTOReq;
import com.dhproject.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    List<OrderDTOReq> findOrdersByUserId(String userId);
}
