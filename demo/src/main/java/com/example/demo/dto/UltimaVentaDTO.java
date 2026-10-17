package com.example.demo.dto;

import java.time.LocalDate;

public record UltimaVentaDTO(

        Long id,
        LocalDate fecha,
        Float total

) {}