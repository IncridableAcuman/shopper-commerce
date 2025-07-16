package com.app.server.service;

import com.app.server.dto.ProductRequest;
import com.app.server.dto.ProductResponse;
import com.app.server.entity.Product;
import com.app.server.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest request){
        Product product=new Product();
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImage(request.getImage());
        product.setCategory(request.getCategory());
        product.setSizes(request.getSizes());
        productRepository.save(product);
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getImage(),
                product.getCategory(),
                product.getSizes()
        );
    }
}
