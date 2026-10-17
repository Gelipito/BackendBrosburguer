package com.example.demo.dto;

import java.util.List;

import lombok.Data;


public record productRequestDTO(

        ProductCreateDTO product,

        List<productIngredientRequestDTO> ingredients

){}
