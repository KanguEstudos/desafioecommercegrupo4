package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Buyer;
import com.curso.desafioecommerce.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;
@RestController
@RequestMapping(value = "/buyers")
public class BuyerController {
    @Autowired
    private BuyerRepository repository;

    @GetMapping
    public List<Buyer> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Buyer findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Buyer insert(@RequestBody Buyer user) {
        return repository.save(user);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Buyer> update(@PathVariable Long id, @RequestBody Buyer buyer) {
        return repository.findById(id).map(
                map -> {
                    map.setName(buyer.getName());
                    map.setEmail(buyer.getEmail());
                    map.setBirthday(buyer.getBirthday());
                    map.setAddress(buyer.getAddress());
                    map.setZipcode(buyer.getZipcode());
                    Buyer saved = repository.save(map);
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
