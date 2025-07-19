package com.app.server.service;

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
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private CartRepository cartRepository;
    private UserRepository userRepository;

    @Transactional
    public Order orderPlace(Long userId){
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

        return saveOrder;
    }
    @Transactional
    public List<Order> getOrders(Long userId){
        return orderRepository.findByUserId(userId);
    }
}
