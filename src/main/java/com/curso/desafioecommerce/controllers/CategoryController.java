package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Category;
import com.curso.desafioecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/products/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<Category> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public Category insert(@RequestBody Category category) {
        return repository.save(category);
    }

    @GetMapping(value = "/{name}")
    public List<Category> findByName(@PathVariable String name) {
        return repository.findByName(name);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        return repository.findById(id).map(
                map -> {
                    map.setName(category.getName());
                    Category saved = repository.save(category);
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
}
