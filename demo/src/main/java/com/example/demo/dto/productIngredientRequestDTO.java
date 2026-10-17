package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record productIngredientRequestDTO(
     

     @NotBlank(message = "Se necesita un Nombre para buscar")
       String name,

      @NotNull(message = "se necesita una cantidad")
       Float requiredQuantity


) {


    
}
