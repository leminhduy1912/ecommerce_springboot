package com.ecommerce.library.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor

public class UserEntity extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;


    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;








    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<RoleEntity> roles;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private CityEntity city;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
    private ShoppingCartEntity cart;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<OrderEntity> orders;



    public UserEntity() {
        this.cart = new ShoppingCartEntity();
    }

}
