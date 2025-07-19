package com.app.server.dto;

public record CartItemDto(
        Long productId,
        String productTitle,
        Double productPrice,
        String productImage,
        int quantity
) {}
