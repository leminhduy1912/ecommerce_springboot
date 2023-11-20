package com.ecommerce.library.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private String id;

    private String name;

    private String description;

    private int currentQuantity;

    private double costPrice;

    private double salePrice;

    private String image;


    private String categoryId;
    private String categoryName;

    private int is_activated;

    private int is_deleted;
}
