package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProductIngredientController {

    @GetMapping("/IngredientesDelProducto")
    public String actualizar(@RequestParam String name) {
        return new String();
    }
    
    
}
