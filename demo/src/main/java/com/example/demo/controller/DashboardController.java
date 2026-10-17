package com.example.demo.controller;

import com.example.demo.dto.DashboardResponseDTO;
import com.example.demo.service.DashboardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponseDTO dashboard(){

        return dashboardService.obtenerDashboard();

    }

}