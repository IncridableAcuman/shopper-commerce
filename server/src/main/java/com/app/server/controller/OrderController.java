package com.app.server.controller;

import com.app.server.dto.OrderResponse;
import com.app.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> orderPlace(@RequestParam Long userId){
        return ResponseEntity.ok(orderService.orderPlace(userId));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam Long userId){
        return ResponseEntity.ok(orderService.getOrders(userId));
    }
}
