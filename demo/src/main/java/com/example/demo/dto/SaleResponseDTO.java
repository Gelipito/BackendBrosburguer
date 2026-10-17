package com.example.demo.dto;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SaleResponseDTO {
    

    @NotBlank(message = "es necesario la fecha")
      LocalDate date;
      @NotBlank(message = "es necesario el total")
      Float  total;



    
}
