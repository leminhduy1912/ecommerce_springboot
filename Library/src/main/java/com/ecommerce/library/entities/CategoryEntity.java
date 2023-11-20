package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
    @Id
    @Column(name = "category_id")
    private String id;
    @Column(name = "category_name")
    private String name;
    @Column(name = "category_code")
    private String code;
    @Column(name = "is_activated")
    private int isActivated;
    @Column(name = "is_deleted")
    private int isDeleted;

//    public CategoryEntity(String name) {
//        this.name = name;
//        this.activated = true;
//        this.deleted = false;
//    }
}
