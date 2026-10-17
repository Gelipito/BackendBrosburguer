package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Sale;

@Repository
public interface SaleDao extends JpaRepository<Sale,Long> {


 
    
} 