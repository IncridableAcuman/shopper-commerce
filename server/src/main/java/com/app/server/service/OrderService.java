package com.app.server.service;

import com.app.server.dto.OrderItemDto;
import com.app.server.dto.OrderResponse;
import com.app.server.entity.Cart;
import com.app.server.entity.Order;
import com.app.server.entity.OrderItem;
import com.app.server.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public OrderResponse orderPlace(Long userId){
        Cart cart=cartRepository.findByUserId(userId).orElseThrow(()->new IllegalArgumentException("Cart not found!"));

        if(cart.getItems().isEmpty()) throw new IllegalArgumentException("Cart is empty!");

        Order order=new Order();
        order.setUser(cart.getUser());
        order.setOrderTime(LocalDateTime.now());
        order.setPrice(cart.getTotal());

        List<OrderItem> orderItems=cart.getItems().stream().map(cartItem->{
            OrderItem item=new OrderItem();
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
            item.setOrder(order);
            return item;
        }).toList();
        order.setItems(orderItems);
        Order saveOrder=orderRepository.save(order);

        cart.getItems().clear();
        cart.setTotal(0.0);
        cartRepository.save(cart);

        List<OrderItemDto> itemDto=orderItems.stream()
                .map(item->new OrderItemDto(item.getId(),item.getProduct().getTitle(),item.getQuantity(),item.getPrice())).toList();

        return new OrderResponse(saveOrder.getId(),saveOrder.getOrderTime(), saveOrder.getPrice(), itemDto);
    }
    @Transactional
    public List<OrderResponse> getOrders(Long userId){
        List<Order> orders=orderRepository.findByUserId(userId);

        return orders.stream()
                .map(order->{
                    List<OrderItemDto> itemDto=order
                            .getItems()
                            .stream()
                            .map(orderItem ->
                                    new OrderItemDto(orderItem.getId(),
                                            orderItem.getProduct().getTitle(),
                                            orderItem.getQuantity(),
                                            orderItem.getPrice()))
                            .toList();
                    return new OrderResponse(order.getId(),order.getOrderTime(),order.getPrice(),itemDto);
                }).toList();
    }
}
