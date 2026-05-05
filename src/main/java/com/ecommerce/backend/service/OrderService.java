package com.ecommerce.backend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ecommerce.backend.dto.OrderRequestDTO;
import com.ecommerce.backend.dto.OrderResponseDTO;
import com.ecommerce.backend.exception.BadRequestException;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        logger.info("Creating order with productIds: {}", request.getProductIds());

        List<Product> products = productRepository.findAllById(request.getProductIds());

        if (products == null || products.isEmpty()) {
            logger.error("Invalid products: {}", request.getProductIds());
            throw new BadRequestException("Product IDs are required or Invalid products provided");
        }

        double total = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        Order order = new Order();
        order.setProducts(products);
        order.setTotal(total);

        Order savedOrder = repository.save(order);

        logger.info("Order created successfully with id: {}", savedOrder.getId());

        List<String> productNames = products.stream()
                .map(Product::getName)
                .toList();

        return new OrderResponseDTO(
                savedOrder.getId(),
                total,
                productNames
        );
    }
}