package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.modelo.Product;
import java.util.List;




@Repository
public interface ProductDao  extends JpaRepository<Product,Long>{

    boolean existsByName(String name);


    Product  findByName(String name);



    
}
