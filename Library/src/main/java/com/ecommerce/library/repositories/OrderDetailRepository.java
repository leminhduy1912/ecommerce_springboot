package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity,Long> {
    @Query(value = "select o from order_details as o where o.order_id = ?1",nativeQuery = true)
    List<OrderDetailEntity> findByOrderId(Long orderId);
}
