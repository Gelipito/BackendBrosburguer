package com.example.demo.repository;

import com.example.demo.modelo.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientDao extends JpaRepository<Ingredient, Long> {

    Ingredient findByName(String name);

    boolean existsByName(String name);

}