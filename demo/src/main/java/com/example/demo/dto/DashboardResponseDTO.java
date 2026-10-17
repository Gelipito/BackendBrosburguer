package com.example.demo.dto;

import java.util.List;

public record DashboardResponseDTO(

        Float ventasHoy,
        Float ventasMes,
        Long productosVendidos,
        Long ingredientesBajos,

        List<UltimaVentaDTO> ultimasVentas,

        List<VentaPorDiaDTO> ventasPorDia,

        List<ProductoMasVendidoDTO> productosMasVendidos

) {}