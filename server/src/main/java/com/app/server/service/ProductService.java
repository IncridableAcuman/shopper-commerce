package com.app.server.service;

import com.app.server.dto.ProductRequest;
import com.app.server.dto.ProductResponse;
import com.app.server.entity.Product;
import com.app.server.enums.Category;
import com.app.server.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse productData(Product product){
        return new  ProductResponse(
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
    @Transactional
    public Product findProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Product not found"));
    }

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
        return productData(product);
    }
    @Transactional
    public List<ProductResponse> getProductByCategory(Category category){
       return  productRepository.findByCategory(category)
               .stream().map(this::productData).toList();
    }
    @Transactional
    public List<ProductResponse> getAll(){
        return  productRepository.findAll()
                .stream().map(this::productData).toList();
    }
    @Transactional
    public ProductResponse getProductById(Long id){
        return (ProductResponse) productRepository.findById(id).stream().map(this::productData);
    }
    @Transactional
    public ProductResponse updateProduct(Long id,ProductRequest request){
        Product product=findProduct(id);
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImage(request.getImage());
        product.setCategory(request.getCategory());
        product.setSizes(request.getSizes());
        productRepository.save(product);
        return productData(product);
    }
    @Transactional
    public void delete(Long id){
        Product product=findProduct(id);
        productRepository.delete(product);
    }
}
