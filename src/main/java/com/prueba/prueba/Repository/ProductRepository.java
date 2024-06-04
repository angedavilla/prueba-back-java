package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.ProductsEntities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductsEntities, Long> {

}
