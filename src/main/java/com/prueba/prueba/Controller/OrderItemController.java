package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.OrderItemsEntities;
import com.prueba.prueba.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/order-items/list")
    public ResponseEntity<List<OrderItemsEntities>> getAllOrderItems(){
        List<OrderItemsEntities> allOrderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(allOrderItems);
    }

    @GetMapping("/order-item/{id}")
    public ResponseEntity<OrderItemsEntities> getOrderItemId(@PathVariable Long id){
        OrderItemsEntities getOrderItemId = orderItemService.getOrderItemsId(id);
        return ResponseEntity.ok(getOrderItemId);
    }

    @PostMapping("/order-item/save")
    public ResponseEntity<OrderItemsEntities> saveOrderItem(@RequestBody OrderItemsEntities orderItemsEntities){
        OrderItemsEntities saveOrderItem = orderItemService.saveOrderItem(orderItemsEntities);
        return ResponseEntity.ok(saveOrderItem);
    }

    @PutMapping("/order-item/update/{id}")
    public ResponseEntity<OrderItemsEntities> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemsEntities orderItemsEntities){
        OrderItemsEntities updateOrderItem = orderItemService.updateOrderItem(id, orderItemsEntities);
        return ResponseEntity.ok(updateOrderItem);
    }

    @DeleteMapping("/order-item/delete/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
