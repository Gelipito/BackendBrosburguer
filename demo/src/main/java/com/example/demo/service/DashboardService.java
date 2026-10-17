package com.example.demo.service;

import com.example.demo.dto.DashboardResponseDTO;
import com.example.demo.repository.DashboardDao;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardDao dashboardDao;

    public DashboardResponseDTO obtenerDashboard(){

        return new DashboardResponseDTO(

                dashboardDao.ventasHoy(),

                dashboardDao.ventasMes(),

                dashboardDao.productosVendidos(),

                dashboardDao.ingredientesBajos(),

                dashboardDao.ultimasVentas(),

                dashboardDao.ventasPorDia(),

                dashboardDao.productosMasVendidos()

        );

    }

}