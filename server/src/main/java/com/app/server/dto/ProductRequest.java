package com.app.server.dto;

import com.app.server.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    @NotBlank(message = "Title must be required!")
    @Size(min = 5,max = 150,message = "Title must between 5 and 150 characters")
    private String title;

    @NotBlank(message = "Description must be required!")
    @Size(min = 10,max = 1050,message = "Description must between 5 and 150 characters")
    private String description;

    @NotNull(message = "Price must be required")
    private Double price;

    private int stock;

    private String image;

    private Category category;

    private List<String> sizes;
}
