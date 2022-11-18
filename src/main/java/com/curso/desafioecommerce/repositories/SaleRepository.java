package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
