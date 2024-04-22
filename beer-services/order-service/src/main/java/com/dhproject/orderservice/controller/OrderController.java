package com.dhproject.orderservice.controller;

import com.dhproject.orderservice.dto.OrderDTOReq;
import com.dhproject.orderservice.model.Order;
import com.dhproject.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderDTOReq orderDTOReq){
        orderService.createOrder(orderDTOReq);
        return "Order placed successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTOReq> getAllOrders(){

        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDTOReq findOrderById(@PathVariable Long orderId){
        return orderService.findOrderById(orderId);
    }
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDTOReq> findByUserId(@PathVariable String userId){
        return orderService.findOrdersByUserId(userId);
    }

}
