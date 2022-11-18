package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
