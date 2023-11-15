package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByIsDeletedAndIsActivated(int isDeleted, int isActivated, Pageable pageable);
    @Query("SELECT p from ProductEntity p WHERE p.isDeleted = 0 and p.isActivated = 1")
    List<ProductEntity> getAllProduct();

    @Query("SELECT p FROM ProductEntity p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    Page<ProductEntity> findByNameOrDescription(String keyword, Pageable pageable);
    @Query(value = "select " +
            "p.product_id, p.name, p.description, p.current_quantity, p.cost_price, p.category_id, p.sale_price, p.image, p.isActivated, p.isDeleted " +
            "from ProductEntity p where p.isActivated = 0 and p.isDeleted = 1 order by rand() limit 9", nativeQuery = true)
    List<ProductEntity> randomProduct();

    @Query(value = "select " +
            "p.product_id, p.name, p.description, p.current_quantity, p.cost_price, p.category_id, p.sale_price, p.image, p.isActivated, p.isDeleted " +
            "from ProductEntity p where p.isDeleted = 0 and p.isActivated = 1 order by p.cost_price desc limit 9", nativeQuery = true)
    List<ProductEntity> filterHighestCostPriceProducts();
    @Query(value = "select " +
            "p.product_id, p.name, p.description, p.current_quantity, p.cost_price, p.category_id, p.sale_price, p.image, p.isActivated, p.isDeleted " +
            "from ProductEntity p where p.isDeleted = 0 and p.isActivated = 1 order by p.cost_price asc limit 9", nativeQuery = true)
    List<ProductEntity> filterLowestCostPriceProducts();
}
