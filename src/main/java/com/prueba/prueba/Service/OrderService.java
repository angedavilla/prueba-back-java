package com.prueba.prueba.Service;

import com.prueba.prueba.Entities.OrderEntities;
import com.prueba.prueba.Entities.OrderItemsEntities;
import com.prueba.prueba.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<OrderEntities> getAllOrders() {
        List<OrderEntities> orderList = (List<OrderEntities>) orderRepository.findAll();
        if (orderList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found");
        }
        return orderList;
    }

    @Transactional(readOnly = true)
    public OrderEntities getOrderId(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id));
    }

    @Transactional
    public OrderEntities saveOrderPromotion(OrderEntities orderEntities, LocalDateTime startTime, LocalDateTime endTime, boolean isRandomOrder) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(startTime) && now.isBefore(endTime)) {
            orderEntities.setDiscount((orderEntities.getDiscount() != null ? orderEntities.getDiscount() : 0) + 10.0);
        }
        if (isRandomOrder) {
            orderEntities.setDiscount((orderEntities.getDiscount() != null ? orderEntities.getDiscount() : 0) + 50.0);
        }
        if (isFrequentCustomer(orderEntities.getUser().getId())) {
            orderEntities.setDiscount((orderEntities.getDiscount() != null ? orderEntities.getDiscount() : 0) + 5.0);
        }
        return orderRepository.save(orderEntities);
    }

    @Transactional
    public OrderEntities saveOrder(OrderEntities orderEntities) {
        return orderRepository.save(orderEntities);
    }

    @Transactional
    public OrderEntities updateOrder(Long id, OrderEntities orderEntities) {
        try {
            OrderEntities existOrder = orderRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id));

            existOrder.setTotal(orderEntities.getTotal());
            existOrder.setDiscount(orderEntities.getDiscount());
            existOrder.setUser(orderEntities.getUser());

            return orderRepository.save(existOrder);
        } catch (Exception e) {
            // Log the exception with more details
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not update order", e);
        }
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean isFrequentCustomer(Long userId) {
        long orderCount = orderRepository.countByUserId(userId);
        return orderCount > 10;
    }

}
