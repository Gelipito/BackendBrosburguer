package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/sales")
public class SaleItemController {
    


    @PostMapping("/registrarSale")
    public String registrarSale(@RequestBody String entity) {
       
        
        return entity;
    }
    



}
