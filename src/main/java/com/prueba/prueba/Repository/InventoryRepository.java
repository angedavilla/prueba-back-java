package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.InventoryEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntities, Long> {

    Optional<InventoryEntities> findByProductId(Long productId);
    boolean existsByProductIdOrProductName(Long productId, String productName);

}
