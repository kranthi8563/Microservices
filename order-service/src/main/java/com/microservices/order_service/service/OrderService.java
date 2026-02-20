package com.microservices.order_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.order_service.entity.Order;
import com.microservices.order_service.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order create(Order order) {
        return repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }
}