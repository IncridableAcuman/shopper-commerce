package com.app.server.service;

import com.app.server.repository.CartItemRepository;
import com.app.server.repository.CartRepository;
import com.app.server.repository.ProductRepository;
import com.app.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
}
