package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.OrderItemsEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItemsEntities, Long> {
}
