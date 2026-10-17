package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductCreateDTO(
    
      @Size(min = 1, message = "El nombre no puede estar vacío si se proporciona")
    String name,
    
    // Si no se envía, pasa la validación. Si se envía, debe ser un número mayor a 0.
    @Positive(message = "El precio de venta debe ser mayor a cero si se proporciona")
    Float sellingPrice
) {}