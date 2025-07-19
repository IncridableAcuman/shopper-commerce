package com.app.server.util;

import com.app.server.dto.CartDto;
import com.app.server.dto.CartItemDto;
import com.app.server.entity.Cart;
import com.app.server.entity.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    public CartDto toDto(Cart cart){
        List<CartItemDto> items=cart.getItems()
                .stream()
                .map(this::toItemDto).toList();
        return new CartDto(cart.getId(), cart.getTotal(), items);
    }

    public CartItemDto toItemDto(CartItem item){
        return new CartItemDto(
                item.getId(),
                item.getProduct().getTitle(),
                item.getProduct().getPrice(),
                item.getProduct().getImage(),
                item.getQuantity()
                );
    }
}
