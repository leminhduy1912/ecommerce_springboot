package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    @Query(value = "select o from orders as  o where o.customer_id = ?1",nativeQuery = true)
    List<OrderEntity> findByCustomerId(Long customerId);
}
