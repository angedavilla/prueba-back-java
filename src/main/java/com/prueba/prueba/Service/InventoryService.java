package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.InventoryEntities;
import com.prueba.prueba.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryEntities> getAllInventory() {
        List<InventoryEntities> inventoryList = (List<InventoryEntities>) inventoryRepository.findAll();
        if (inventoryList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No inventory items found");
        }
        return inventoryList;
    }

    @Transactional(readOnly = true)
    public InventoryEntities getInventoryId(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory item not found with ID: " + id));
    }

    @Transactional
    public InventoryEntities saveInventory(InventoryEntities inventoryEntities) {
        return inventoryRepository.save(inventoryEntities);
    }

    @Transactional
    public InventoryEntities updateInventory(Long id, InventoryEntities inventoryEntities) {
        InventoryEntities existInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory item not found with ID: " + id));

        existInventory.setQuantity(inventoryEntities.getQuantity());
        existInventory.setProduct(inventoryEntities.getProduct());

        return inventoryRepository.save(existInventory);
    }

    @Transactional
    public void deleteInventory(Long id) {
        if (!inventoryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory item not found with ID: " + id);
        }
        inventoryRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean checkProductExists(Long productId, String productName) {
        return inventoryRepository.existsByProductIdOrProductName(productId, productName);
    }
}
