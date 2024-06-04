package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.ProductsEntities;
import com.prueba.prueba.Service.CustomConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/custom")
public class CustomConsultController {

    @Autowired
    private CustomConsultService customConsultService;

    @GetMapping("/active-products")
    public ResponseEntity<List<ProductsEntities>> getActiveProducts() {
        List<ProductsEntities> activeProducts = customConsultService.getActiveProducts();
        return ResponseEntity.ok(activeProducts);
    }

    @GetMapping("/top-selling-products")
    public ResponseEntity<List<Object[]>> getTopSellingProducts() {
        List<Object[]> topSellingProducts = customConsultService.getTopSellingProducts();
        return ResponseEntity.ok(topSellingProducts);
    }

    @GetMapping("/frequent-customers")
    public ResponseEntity<List<Object[]>> getFrequentCustomers() {
        List<Object[]> frequentCustomers = customConsultService.getFrequentCustomers();
        return ResponseEntity.ok(frequentCustomers);
    }
}
