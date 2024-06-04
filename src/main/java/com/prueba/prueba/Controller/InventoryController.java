package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.InventoryEntities;
import com.prueba.prueba.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventories/list")
    public ResponseEntity<List<InventoryEntities>> getAllInventory(){
        List<InventoryEntities> allInventory = inventoryService.getAllInventory();
        return ResponseEntity.ok(allInventory);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryEntities> getInventoryId(@PathVariable Long id){
        InventoryEntities getInventoryId = inventoryService.getInventoryId(id);
        return ResponseEntity.ok(getInventoryId);
    }

    @GetMapping("/inventory/check-exists")
    public ResponseEntity<Boolean> checkProductExists(@RequestParam Long productId, @RequestParam String productName) {
        boolean exists = inventoryService.checkProductExists(productId, productName);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/inventory/save")
    public ResponseEntity<InventoryEntities> saveInventory(@RequestBody InventoryEntities inventoryEntities){
        InventoryEntities saveInventory = inventoryService.saveInventory(inventoryEntities);
        return ResponseEntity.ok(saveInventory);
    }

    @PutMapping("/inventory/update/{id}")
    public ResponseEntity<InventoryEntities> updateInventory(@PathVariable Long id, @RequestBody InventoryEntities inventoryEntities){
        InventoryEntities updateInventory = inventoryService.updateInventory(id, inventoryEntities);
        return ResponseEntity.ok(updateInventory);
    }

    @DeleteMapping("/inventory/delete/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id){
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
