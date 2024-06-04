package com.prueba.prueba.Controller;

import com.prueba.prueba.Entities.OrderEntities;
import com.prueba.prueba.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/list")
    public ResponseEntity<List<OrderEntities>> getAllOrders(){
        List<OrderEntities> getAllOrders = orderService.getAllOrders();
        return ResponseEntity.ok(getAllOrders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderEntities> getOrderId(@PathVariable Long id){
        OrderEntities getOrderId = orderService.getOrderId(id);
        return ResponseEntity.ok(getOrderId);
    }

    @PostMapping("/order/save/promotion")
    public ResponseEntity<OrderEntities> saveOrder(
            @RequestBody OrderEntities orderEntities,
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
            @RequestParam("isRandomOrder") boolean isRandomOrder) {
        OrderEntities savedOrder = orderService.saveOrderPromotion(orderEntities, startTime, endTime, isRandomOrder);
        return ResponseEntity.ok(savedOrder);
    }

    @PostMapping("/order/save")
    public ResponseEntity<OrderEntities> saveOrder(@RequestBody OrderEntities orderEntities){
        OrderEntities saveOrder = orderService.saveOrder(orderEntities);
        return ResponseEntity.ok(saveOrder);
    }

    @PutMapping("/order/update/{id}")
    public ResponseEntity<OrderEntities> updateOrder(@PathVariable Long id, @RequestBody OrderEntities orderEntities){
        OrderEntities updateOrder = orderService.updateOrder(id, orderEntities);
        return ResponseEntity.ok(updateOrder);
    }

    @DeleteMapping("/order/delete/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
