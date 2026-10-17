package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record IngredientRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        String name,

        @NotNull(message = "El stock es obligatorio")
        Float stock,

        @NotNull(message = "El precio es obligatorio")
        Float purchasePrice,

        @NotNull(message = "La fecha de vencimiento es obligatoria")
        LocalDate expirationDate

) {}