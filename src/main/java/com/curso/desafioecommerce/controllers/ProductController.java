package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Product;
import com.curso.desafioecommerce.repositories.ProductRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Api(tags = { "Products" })
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<Product> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Product findById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Product insert(@RequestBody Product product) {
        return repository.save(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return repository.findById(id).map(
                map -> {
                    map.setTittle(product.getTittle());
                    map.setDescription(product.getDescription());
                    map.setPrice(product.getPrice());
                    map.setCategory(product.getCategory());
                    map.setImage(product.getImage());
                    Product saved = repository.save(map);
                    return ResponseEntity.ok().body(saved);
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repository.findById(id).map(
                map -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }
        ).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{name}")
    public List<Product> listByCategory(@PathVariable String name) {
        return repository.findAllByCategory_Name(name);
    }
}
