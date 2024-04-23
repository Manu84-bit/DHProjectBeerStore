package com.dhproject.orderservice.service;

import com.dhproject.orderservice.dto.OrderDTOReq;
import com.dhproject.orderservice.dto.OrderLineItemsDTO;
import com.dhproject.orderservice.model.Order;
import com.dhproject.orderservice.model.OrderLineItems;
import com.dhproject.orderservice.repository.IOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    private final IOrderRepository orderRepository;
    private final WebClient.Builder webClient;

    public OrderService(IOrderRepository orderRepository, WebClient.Builder webClient) {
        this.orderRepository = orderRepository;
        this.webClient = webClient;
    }
    public void createOrder(OrderDTOReq orderDTOReq) {
        Order order = new Order();
        order.setUserId(orderDTOReq.getUserId());
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItems(
                orderDTOReq.getOrderLineItemsDTOList().stream()
                        .map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO)).toList()
        );

        String inventoryURLGet = "http://inventory-service/api/inventory?stockCode=";

        //Call inventory service and place order if product is in store
        for(OrderLineItems orderLineItems: order.getOrderLineItems()){
            String inventoryURLGetSku = inventoryURLGet + orderLineItems.getStockCode();
            Integer result = webClient.build().get().uri(inventoryURLGetSku)
                    .retrieve()
                    .bodyToMono(Integer.class)
                    .block();
            if(result < orderLineItems.getQuantity()){
                throw new IllegalArgumentException("Producto con SKU code "
                        + orderLineItems.getStockCode() + " no tiene suficientes unidades.");
            }
        }


        orderRepository.save(order);


    }

    public List<OrderDTOReq> getAllOrders(){
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(o-> mapToOrderDTO(o)).toList();
    }

    public OrderDTOReq findOrderById (Long id){
        return mapToOrderDTO(orderRepository.findById(id).get());
    }

    public List<OrderDTOReq> findOrdersByUserId(String userId){
        return orderRepository.findAll().stream().filter(order ->
            order.getUserId().equals(userId)
        ).map(this::mapToOrderDTO).toList();
    }

    public void deleteOrderByOrderLineID(Long orderId){
        Optional<Order> order = orderRepository.findAll().stream().filter(
                o -> o.getOrderLineItems().get(0).getOrderLineId().equals(orderId))
                .findFirst();
        if(order.isPresent()){
            orderRepository.delete(order.get());
        } else {
            throw new IllegalArgumentException("Orden no encontrada");
        }

    }
    private OrderDTOReq mapToOrderDTO(Order order) {
        OrderDTOReq orderDTOReq = new OrderDTOReq();
        orderDTOReq.setUserId(order.getUserId());
        orderDTOReq.setOrderLineItemsDTOList(
                order.getOrderLineItems().stream()
                        .map(orderLineItems -> mapToModel(orderLineItems)).toList()
        );
        return orderDTOReq;
    }

    private OrderLineItemsDTO mapToModel(OrderLineItems orderLineItems) {
        OrderLineItemsDTO orderLineItemsDTO = new OrderLineItemsDTO();
        orderLineItemsDTO.setOrderLineId(orderLineItems.getOrderLineId());
        orderLineItemsDTO.setQuantity(orderLineItems.getQuantity());
        orderLineItemsDTO.setPrice(orderLineItems.getPrice());
        orderLineItemsDTO.setStockCode(orderLineItems.getStockCode());

        return orderLineItemsDTO;
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setStockCode(orderLineItemsDTO.getStockCode());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        return orderLineItems;
    }
}
