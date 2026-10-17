package com.example.demo.repository;

import com.example.demo.dto.ProductoMasVendidoDTO;
import com.example.demo.dto.UltimaVentaDTO;
import com.example.demo.dto.VentaPorDiaDTO;
import com.example.demo.modelo.Sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface DashboardDao extends JpaRepository<Sale, Long> {

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE s.date = CURRENT_DATE
    """)
    Float ventasHoy();

    @Query("""
        SELECT COALESCE(SUM(s.total),0)
        FROM Sale s
        WHERE YEAR(s.date)=YEAR(CURRENT_DATE)
        AND MONTH(s.date)=MONTH(CURRENT_DATE)
    """)
    Float ventasMes();

    @Query("""
        SELECT COALESCE(SUM(si.quantity),0)
        FROM SaleItem si
    """)
    Long productosVendidos();

    @Query("""
        SELECT COUNT(i)
        FROM Ingredient i
        WHERE i.stock < 20
    """)
    Long ingredientesBajos();

    @Query("""
        SELECT new com.example.demo.dto.UltimaVentaDTO(
            s.id,
            s.date,
            s.total
        )
        FROM Sale s
        ORDER BY s.date DESC
    """)
    List<UltimaVentaDTO> ultimasVentas();

    @Query("""
        SELECT new com.example.demo.dto.VentaPorDiaDTO(

            s.date,

            SUM(s.total)

        )

        FROM Sale s

        GROUP BY s.date

        ORDER BY s.date
    """)
    List<VentaPorDiaDTO> ventasPorDia();

    @Query("""
        SELECT new com.example.demo.dto.ProductoMasVendidoDTO(

            p.name,

            SUM(si.quantity)

        )

        FROM SaleItem si

        JOIN si.product p

        GROUP BY p.name

        ORDER BY SUM(si.quantity) DESC
    """)
    List<ProductoMasVendidoDTO> productosMasVendidos();

}