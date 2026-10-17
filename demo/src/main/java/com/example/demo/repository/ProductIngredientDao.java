package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.Ingredient;
import com.example.demo.modelo.Product;
import com.example.demo.modelo.ProductIngredient;
import java.util.List;



@Repository
public interface ProductIngredientDao extends JpaRepository<ProductIngredient,Long> {
  
// Spring lo genera automáticamente en base al nombre
List<ProductIngredient> findByProduct(Product product);


 
    
}
