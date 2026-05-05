package com.ecommerce.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.backend.dto.ApiResponse;
import com.ecommerce.backend.dto.OrderRequestDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }


    @PostMapping
    public ApiResponse<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO request) {

        OrderResponseDTO response = service.createOrder(request);

        return new ApiResponse<>(
                true,
                response,
                "Order created successfully"
        );
    }
}