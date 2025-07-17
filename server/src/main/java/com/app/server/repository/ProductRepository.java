package com.app.server.repository;

import com.app.server.entity.Product;
import com.app.server.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory(Category category);
}
