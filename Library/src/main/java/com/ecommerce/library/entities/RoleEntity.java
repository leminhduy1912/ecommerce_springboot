package com.ecommerce.library.entities;




import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity extends BaseEntity {
    @Id
    @Column(name = "role_id")
    private String id;
    @Column(name = "role_name")
    private String name;
    @Column(name = "role_code")
    private String code;

}