package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.ProductsEntities;
import com.prueba.prueba.Repository.CustomConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomConsultService {

    @Autowired
    private CustomConsultRepository customRepository;

    public List<ProductsEntities> getActiveProducts() {
        List<ProductsEntities> products = customRepository.findActiveProducts();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No active products found");
        }
        return products;
    }

    public List<Object[]> getTopSellingProducts() {
        List<Object[]> products = customRepository.findTopSellingProducts();
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No top-selling products found");
        }
        return products;
    }

    public List<Object[]> getFrequentCustomers() {
        List<Object[]> customers = customRepository.findFrequentCustomers();
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No frequent customers found");
        }
        return customers;
    }
}
