package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemCarritoDTO;
import com.example.demo.dto.SaleResponseDTO;
import com.example.demo.modelo.Product;
import com.example.demo.modelo.Sale;
import com.example.demo.service.SaleService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/sales")

public class SaleController {

    private  final SaleService saleService;
    @PostMapping("/registrarVenta")
    public Sale regitrarVenta(@RequestBody List<ItemCarritoDTO> products,@RequestParam String ccCliente){
        Sale sale=saleService.registrarSale(products,ccCliente);

        return sale;


        

    }
    @GetMapping("/obtenerTodaslasventas")
    public ResponseEntity<List<Sale>> todaslasventas() {
        return ResponseEntity.ok(saleService.TraerVentas());
        
    }
    
    
}
