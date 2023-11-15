package com.ecommerce.library.repositories;

import com.ecommerce.library.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    @Query("SELECT u FROM UserEntity u JOIN FETCH u.roles WHERE u.username = :username")
    UserEntity findByUserNameWithRoles(@Param("username") String username);

}
