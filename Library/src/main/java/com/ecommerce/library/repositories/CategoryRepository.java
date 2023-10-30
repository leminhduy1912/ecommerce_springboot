package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    @Query(value = "UPDATE CategoryEntity SET name = ?1,code=?2 WHERE id = ?3")
    CategoryEntity update(String name, String code ,Long id);
    @Query(value = "SELECT * from categories WHERE is_activated = 1", nativeQuery = true)
    List<CategoryEntity> findAllByActivatedTrue();
}
