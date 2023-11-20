package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "countries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity extends BaseEntity {
    @Id
    @Column(name = "country_id")
    private String id;

    @Column(name = "country_name")
    private String name;

    @Column(name = "country_code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity city;
}