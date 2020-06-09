package com.junit.junit.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.junit.model.Product;
import com.junit.junit.service.ProductService;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping({"/api/v1/products"})
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
        return ResponseEntity.ok(productService.createOrUpdate(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {

        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
         //   log.error("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product.get());
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Product> update(@PathVariable long id, @Valid @RequestBody Product product) {

        Optional<Product> p = productService.findById(id);

        if (!p.isPresent()) {
          // log.error("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        p.get().setName(product.getName());
        p.get().setPrice(product.getPrice());
        p.get().setDescription(product.getDescription());

        return ResponseEntity.ok(productService.createOrUpdate(p.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        Optional<Product> p = productService.findById(id);

        if (!p.isPresent()) {
           // log.error("Product with id " + id + " does not exist.");
            return ResponseEntity.notFound().build();
        }

        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
