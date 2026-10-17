package com.example.demo.dto;

// Este record solo sirve para transportar los datos que vienen del JSON
public record ItemCarritoDTO(
    Long productId,
    Integer quantity
   
) {}
