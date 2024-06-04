package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.OrderEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntities, Long> {

    long countByUserId(Long userId);

}
