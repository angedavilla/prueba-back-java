package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.InventoryEntities;
import com.prueba.prueba.Entities.OrderItemsEntities;
import com.prueba.prueba.Repository.InventoryRepository;
import com.prueba.prueba.Repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<OrderItemsEntities> getAllOrderItems() {
        List<OrderItemsEntities> orderItemsList = (List<OrderItemsEntities>) orderItemRepository.findAll();
        if (orderItemsList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No order items found");
        }
        return orderItemsList;
    }

    @Transactional(readOnly = true)
    public OrderItemsEntities getOrderItemsId(Long id){
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found with ID: " + id));
    }

    @Transactional
    public OrderItemsEntities saveOrderItem(OrderItemsEntities orderItemsEntities) {
        InventoryEntities inventory = inventoryRepository.findByProductId(orderItemsEntities.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found for product ID " + orderItemsEntities.getProduct().getId()));

        if (inventory.getQuantity() < orderItemsEntities.getQuantity()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough inventory for product ID " + orderItemsEntities.getProduct().getId());
        }

        inventory.setQuantity(inventory.getQuantity() - orderItemsEntities.getQuantity());
        inventoryRepository.save(inventory);

        return orderItemRepository.save(orderItemsEntities);
    }

    @Transactional
    public OrderItemsEntities updateOrderItem(Long id, OrderItemsEntities orderItemsEntities) {
        OrderItemsEntities existOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found with ID: " + id));

        int oldQuantity = existOrderItem.getQuantity();
        int newQuantity = orderItemsEntities.getQuantity();

        InventoryEntities inventory = inventoryRepository.findByProductId(existOrderItem.getProduct().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inventory not found for product ID " + existOrderItem.getProduct().getId()));

        int updatedQuantity = inventory.getQuantity() + oldQuantity - newQuantity;

        if (updatedQuantity < 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough inventory for product ID " + existOrderItem.getProduct().getId());
        }

        existOrderItem.setQuantity(newQuantity);
        existOrderItem.setPrice(orderItemsEntities.getPrice());
        existOrderItem.setOrder(orderItemsEntities.getOrder());
        existOrderItem.setProduct(orderItemsEntities.getProduct());

        inventory.setQuantity(updatedQuantity);
        inventoryRepository.save(inventory);

        return orderItemRepository.save(existOrderItem);
    }


    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found with ID: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}
