package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
