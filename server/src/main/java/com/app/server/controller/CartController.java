package com.app.server.controller;

import com.app.server.dto.CartDto;
import com.app.server.service.CartService;
import com.app.server.util.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    @GetMapping
    public ResponseEntity<CartDto> getCartForUser(@RequestParam Long userId){
        return ResponseEntity.ok(cartMapper.toDto(cartService.getCartForUser(userId)));
    }
    @PostMapping("/add")
    public ResponseEntity<CartDto> addToCart(@RequestParam Long userId,@RequestParam Long productId,@RequestParam int quantity){
        return ResponseEntity.ok(cartMapper.toDto(cartService.addToCart(userId,productId,quantity)));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<CartDto> removeFromCart(@RequestParam Long userId,@RequestParam Long productId){
        return ResponseEntity.ok(cartMapper.toDto(cartService.removeFromCart(userId,productId)));
    }
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId){
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
