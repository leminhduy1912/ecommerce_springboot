package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUsername(String username);
}
