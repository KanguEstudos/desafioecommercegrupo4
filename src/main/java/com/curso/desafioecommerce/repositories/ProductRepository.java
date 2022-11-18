package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Name(String category_name);

}
