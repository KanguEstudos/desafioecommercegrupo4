package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Category;
import com.curso.desafioecommerce.repositories.CategoryRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@Api(tags = { "Categories" })
@RequestMapping(value = "/products/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @GetMapping
    public List<String> findAll() {
        return repository.findAll()
                .stream()
                .map(Category::getName)
                .toList();
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
                    Category saved = repository.save(map);
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
