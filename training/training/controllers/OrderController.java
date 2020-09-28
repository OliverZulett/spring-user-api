package com.milankas.training.controllers;

import com.milankas.training.dtos.order.PatchOrderInputDTO;
import com.milankas.training.dtos.order.PostOrderInputDTO;
import com.milankas.training.dtos.order.OrderOutputDTO;
import com.milankas.training.exceptions.ResourceNotFoundException;
import com.milankas.training.services.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderOutputDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderOutputDTO> getOrderById(@Valid @PathVariable(value = "id") UUID orderId) throws ResourceNotFoundException {
        OrderOutputDTO orderFound = orderService.findOrderById(orderId);
        if (orderFound == null) throw new ResourceNotFoundException("Order not found for id: " + orderId);
        return ResponseEntity.ok(orderFound);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderOutputDTO> createOrder(@Valid @RequestBody PostOrderInputDTO orderToSave) {
        return new ResponseEntity<>(orderService.saveOrder(orderToSave), HttpStatus.CREATED);
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<OrderOutputDTO> updateOrder(@Valid @PathVariable(value = "id") UUID orderId, @Valid @RequestBody PatchOrderInputDTO orderForUpdate) throws ResourceNotFoundException {
        OrderOutputDTO orderUpdated = orderService.updateOrderById(orderId, orderForUpdate);
        if (orderUpdated == null) throw new ResourceNotFoundException("Order not found for id: " + orderId);
        return new ResponseEntity<>(orderUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") UUID orderId) throws ResourceNotFoundException {
        Boolean orderDeleted = orderService.deleteOrderById(orderId);
        if (orderDeleted == null) throw new ResourceNotFoundException("Order not found for id: " + orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
