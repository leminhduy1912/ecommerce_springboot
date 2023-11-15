package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_current_quantity")
    private int currentQuantity;
    @Column(name = "product_cost_price")
    private double costPrice;
    @Column(name = "product_sale_price")
    private double salePrice;
    @Column(name="product_image")
    private String image;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private CategoryEntity category;
    @Column(name = "product_status")
    private int isActivated;
    @Column(name = "product_deleted_status")
    private int isDeleted;

}
