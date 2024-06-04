package com.prueba.prueba.Repository;

import com.prueba.prueba.Entities.ProductsEntities;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomConsultRepository extends CrudRepository<ProductsEntities, Long> {

    @Query("SELECT p FROM ProductsEntities p WHERE p.active = TRUE")
    List<ProductsEntities> findActiveProducts();

    @Query("SELECT p.id, p.name, SUM(oi.quantity) as totalSold " +
            "FROM ProductsEntities p JOIN p.orderItems oi " +
            "GROUP BY p.id, p.name " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProducts();

    @Query("SELECT u.id, u.username, COUNT(o.id) as totalOrders " +
            "FROM UserEntities u JOIN u.orders o " +
            "GROUP BY u.id, u.username " +
            "ORDER BY totalOrders DESC")
    List<Object[]> findFrequentCustomers();

}
