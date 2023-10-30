package com.ecommerce.library.dtos;

import com.ecommerce.library.entities.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private int currentQuantity;

    private double costPrice;

    private double salePrice;

    private String image;


    private Long categoryId;
    private String categoryName;

    private int is_activated;

    private int is_deleted;
}
