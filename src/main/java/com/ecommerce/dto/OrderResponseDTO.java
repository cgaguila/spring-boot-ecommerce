package com.ecommerce.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private Double total;
    private List<String> productNames;
}