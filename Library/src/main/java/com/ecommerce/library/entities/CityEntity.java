package com.ecommerce.library.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity extends BaseEntity {
    @Id
    @Column(name = "city_id")
    private UUID id;

    @Column(name = "city_name")
    private String name;

    @Column(name = "city_code")
    private String code;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Set<CountryEntity> countries;
}
