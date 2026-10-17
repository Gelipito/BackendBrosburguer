package com.example.demo.dto;

import java.time.LocalDate;

public record VentaPorDiaDTO(

        LocalDate fecha,
        Double ventas

) {}