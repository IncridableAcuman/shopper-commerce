package com.app.server.dto;

import com.app.server.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private int stock;
    private String image;
    private Category category;
    private List<String> sizes;
}
