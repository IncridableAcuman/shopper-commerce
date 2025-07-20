package com.app.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponse {
    private Long orderId;
    private LocalDateTime orderTime;
    private Double price;
    List<OrderItemDto> items;
}
