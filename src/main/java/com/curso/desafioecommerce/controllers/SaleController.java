package com.curso.desafioecommerce.controllers;

import com.curso.desafioecommerce.entities.Sale;
import com.curso.desafioecommerce.repositories.SaleRepository;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = { "Sales" })
@RequestMapping(value = "/sales")
public class SaleController {
    @Autowired
    private SaleRepository repository;
    static final char inicialStatus = 'A';

    @GetMapping
    public List<Sale> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Sale findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    public Sale insert(@RequestBody Sale sale) {
        return repository.save(sale);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Sale> update(@PathVariable Long id, @RequestBody Sale Sale) {
        return repository.findById(id).map(
                map -> {
                    map.setProduct(Sale.getProduct());
                    map.setSeller(Sale.getSeller());
                    map.setBuyer(Sale.getBuyer());
                    map.setPrice(Sale.getPrice());
                    map.setStatus(inicialStatus);
                    Sale saved = repository.save(map);
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
