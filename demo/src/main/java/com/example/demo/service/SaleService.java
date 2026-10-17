package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ItemCarritoDTO;
import com.example.demo.modelo.Sale;
import com.example.demo.modelo.SaleItem;
import com.example.demo.repository.ProductDao;
import com.example.demo.repository.ProductIngredientDao;
import com.example.demo.repository.SaleDao;
import com.example.demo.repository.SaleItemDao;

import jakarta.transaction.Transactional;

import com.example.demo.modelo.Product;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final ProductDao productDao;
    private final ProductIngredientDao productIngredientDao;
    private final SaleDao saleDao;
    private final SaleItemDao saleItemDao;
    private final ProductIngredientService productIngredientService;

  
    

    @Transactional
    public Sale registrarSale(List<ItemCarritoDTO> products,String ccCliente){

        //registrarSale
       
        Sale sale= new Sale();
        sale.setDate(LocalDate.now());
        sale.setCcCliente(ccCliente);
        saleDao.save(sale);

        //Encontrar Producto
        Float totalVenta = 0.0f; 
        for (ItemCarritoDTO i : products) {
             SaleItem saleItem=new SaleItem();
             Product productofound= productDao.findById(i.productId()).orElseThrow(()-> new RuntimeException("no se encuentra producto solicitado"));
              //registrar SaleItem
            Integer cantidad=i.quantity();
            Float precio=productofound.getSellingPrice();
            totalVenta+=  cantidad *precio;

            saleItem.setProduct(productofound);
            saleItem.setQuantity(cantidad);
            saleItem.setUnitPrice(precio);
            saleItem.setSale(sale);
            productIngredientService.restarCantidad(productofound, cantidad);
            

            //Guardarlo en la base de datos
            saleItemDao.save(saleItem);
        }
        sale.setTotal(totalVenta);

        return saleDao.save(sale);
        
    }

    public List<Sale> TraerVentas(){
        List<Sale> sales=saleDao.findAll();
        return sales;



    }


    
}
