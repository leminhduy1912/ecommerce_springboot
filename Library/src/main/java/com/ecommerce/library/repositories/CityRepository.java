package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity,Long> {

}
