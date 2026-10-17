package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

import com.example.demo.dtoML.PredictionRequestDTO;
import com.example.demo.dtoML.PredictionResponseDTO;
import com.example.demo.service.PredictionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/predictions")
@RequiredArgsConstructor
public class PredictionController {

    private final PredictionService predictionService;

    @PostMapping
    public PredictionResponseDTO predict(

            @RequestBody PredictionRequestDTO dto){

        return predictionService.predict(dto);

    }

}
