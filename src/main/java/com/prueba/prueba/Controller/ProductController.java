package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.ProductsEntities;
import com.prueba.prueba.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/products/list")
    public ResponseEntity<List<ProductsEntities>> getAllProducts(){
        List<ProductsEntities> Allproducts = productService.getAllProducts();
        return ResponseEntity.ok(Allproducts);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductsEntities> getProductId(@PathVariable Long id){
        ProductsEntities productId = productService.getProductId(id);
        return ResponseEntity.ok(productId);
    }

    @PostMapping("/product/save")
    public ResponseEntity<ProductsEntities> saveProduct(@RequestBody ProductsEntities productsEntities){
        ProductsEntities saveProduct = productService.saveProduct(productsEntities);
        return ResponseEntity.ok(saveProduct);
    }

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ProductsEntities> updateProduct(@PathVariable Long id, @RequestBody ProductsEntities productsEntities){
        ProductsEntities updateProduct = productService.updateProduct(id, productsEntities);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
