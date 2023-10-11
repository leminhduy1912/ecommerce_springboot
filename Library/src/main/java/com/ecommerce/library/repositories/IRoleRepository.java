package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByName(String name);
    RoleEntity findByCode(String code);
}
