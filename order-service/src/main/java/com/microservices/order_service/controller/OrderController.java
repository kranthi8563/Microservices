package com.microservices.order_service.controller;

import com.microservices.order_service.entity.Order;
import com.microservices.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order create(@RequestBody Order order) {
        return service.create(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return service.getAll();
    }
}