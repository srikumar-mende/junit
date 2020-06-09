package com.junit.junit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.junit.model.Product;
import com.junit.junit.repo.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Product createOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(long id) {
        productRepository.deleteById(id);
    }
}