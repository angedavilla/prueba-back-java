package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.ProductsEntities;
import com.prueba.prueba.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductsEntities> getAllProducts() {
        List<ProductsEntities> productList = (List<ProductsEntities>) productRepository.findAll();
        if (productList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products found");
        }
        return productList;
    }

    @Transactional(readOnly = true)
    public ProductsEntities getProductId(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));
    }

    @Transactional
    public ProductsEntities saveProduct(ProductsEntities productsEntities) {
        return productRepository.save(productsEntities);
    }

    @Transactional
    public ProductsEntities updateProduct(Long id, ProductsEntities productsEntities) {
        ProductsEntities existProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id));

        existProduct.setName(productsEntities.getName());
        existProduct.setDescription(productsEntities.getDescription());
        existProduct.setPrice(productsEntities.getPrice());
        existProduct.setActive(productsEntities.getActive());

        return productRepository.save(existProduct);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with ID: " + id);
        }
        productRepository.deleteById(id);
    }

}
