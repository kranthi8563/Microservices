package com.microservices.product_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.product_service.entity.Product;
import com.microservices.product_service.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
}