package com.app.server.controller;

import com.app.server.entity.Cart;
import com.app.server.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> getCartForUser(@RequestParam Long userId){
        return ResponseEntity.ok(cartService.getCartForUser(userId));
    }
    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId,@RequestParam Long productId,@RequestParam int quantity){
        return ResponseEntity.ok(cartService.addToCart(userId,productId,quantity));
    }
    @DeleteMapping("/remove")
    public ResponseEntity<Cart> removeFromCart(@RequestParam Long userId,@RequestParam Long productId){
        return ResponseEntity.ok(cartService.removeFromCart(userId,productId));
    }
    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId){
        cartService.clearCart(userId);
        return ResponseEntity.ok().build();
    }
}
