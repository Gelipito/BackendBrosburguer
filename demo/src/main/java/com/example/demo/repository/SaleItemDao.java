package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.example.demo.modelo.SaleItem;

@Repository
public interface SaleItemDao extends  JpaRepository<SaleItem,Long> {
    
}
