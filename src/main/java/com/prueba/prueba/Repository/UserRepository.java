package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.UserEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntities, Long> {

    @Query("SELECT u FROM UserEntities u LEFT JOIN FETCH u.roles WHERE u.id = :id")
    Optional<UserEntities> findByIdWithRoles(@Param("id") Long id);

    UserEntities findByUsername(String username);
}
