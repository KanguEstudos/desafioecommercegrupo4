package com.curso.desafioecommerce.repositories;

import com.curso.desafioecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByName(String name);
    
    List<Category> findAll();

}

