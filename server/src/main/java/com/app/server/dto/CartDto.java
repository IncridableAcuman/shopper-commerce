package com.app.server.dto;

import java.util.List;

public record CartDto(
        Long userId,
        Double total,
        List<CartItemDto> items
) {
}
