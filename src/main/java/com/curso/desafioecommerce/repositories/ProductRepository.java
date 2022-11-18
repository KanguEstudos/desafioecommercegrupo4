package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
