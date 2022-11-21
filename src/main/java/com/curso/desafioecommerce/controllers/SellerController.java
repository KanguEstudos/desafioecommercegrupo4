package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Seller;
import com.curso.desafioecommerce.repositories.SellerRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = { "Sellers" })
@RequestMapping(value = "/sellers")
public class SellerController {
    @Autowired
    private SellerRepository repository;

    @GetMapping
    public List<Seller> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Seller findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Seller insert(@RequestBody Seller user) {
        return repository.save(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Seller> update(@PathVariable Long id, @RequestBody Seller seller) {
        return repository.findById(id).map(
                map -> {
                    map.setName(seller.getName());
                    map.setDescription(seller.getDescription());
                    map.setImage(seller.getImage());
                    Seller saved = repository.save(map);
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
