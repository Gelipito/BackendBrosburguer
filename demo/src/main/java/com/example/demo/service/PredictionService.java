package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.example.demo.dtoML.PredictionRequestDTO;
import com.example.demo.dtoML.PredictionResponseDTO;


@Service
public class PredictionService {

    private final RestClient restClient;

    public PredictionService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:9000").build();
    }

    public PredictionResponseDTO predict(PredictionRequestDTO request){

        return restClient.post()

                .uri("/predict")

                .body(request)

                .retrieve()

                .body(PredictionResponseDTO.class);
    }

}
