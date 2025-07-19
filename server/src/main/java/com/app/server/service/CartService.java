package com.app.server.service;

import com.app.server.entity.Cart;
import com.app.server.entity.CartItem;
import com.app.server.entity.Product;
import com.app.server.entity.User;
import com.app.server.repository.CartRepository;
import com.app.server.repository.ProductRepository;
import com.app.server.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Cart getCartForUser(Long userId){
        return cartRepository.findByUserId(userId).orElseGet(()->{
            User user=userRepository.findById(userId).orElseThrow();
            Cart cart=new Cart();
            cart.setUser(user);
            cart.setItems(new ArrayList<>());
            cart.setTotal(0.0);
            return cartRepository.save(cart);
        });
    }
    @Transactional
    public Cart addToCart(Long userId,Long productId,int quantity){
        Cart cart=getCartForUser(userId);
        Product product=productRepository.findById(productId).orElseThrow();
        Optional<CartItem> existingItem=cart.getItems()
                .stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst();
        if(existingItem.isPresent()){
            existingItem.get().setQuantity(existingItem.get().getQuantity()+quantity);
        }else{
            CartItem cartItem=new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cart.getItems().add(cartItem);
        }
        double total=cart.getItems()
                .stream()
                .mapToDouble(item->item.getProduct().getPrice()*item.getQuantity()).sum();
        cart.setTotal(total);
        return cartRepository.save(cart);
    }
    @Transactional
    public Cart removeFromCart(Long userId,Long productId){
        Cart cart=getCartForUser(userId);
        cart.getItems().removeIf(item->item.getProduct().getId().equals(productId));
        double total=cart.getItems().stream()
                .mapToDouble(item->item.getProduct().getPrice()*item.getQuantity()).sum();
        cart.setTotal(total);
        return cartRepository.save(cart);
    }
    @Transactional
    public void clearCart(Long userId){
        Cart cart=getCartForUser(userId);
        cart.getItems().clear();
        cart.setTotal(0.0);
        cartRepository.save(cart);
    }
}
