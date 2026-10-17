package com.example.demo.controller;

import com.example.demo.dto.productRequestDTO;
import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.productIngredientRequestDTO;
import com.example.demo.modelo.Product;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/productos")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/registrarproducto")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody productRequestDTO productoWithIngredients) {
        // Recibes el DTO validado y se lo pasas al servicio
        Product productoNuevo = productService.registerProduct(productoWithIngredients);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED); // 201 CREATED es mejor para POST
    }

    @GetMapping("/buscarProducto")
    public ResponseEntity searchProduct(@RequestParam String name) {

        Product p= productService.findProduct(name);
        return ResponseEntity.ok(p);
       

    }

     @PutMapping("/actualizar/{name}")
    public ResponseEntity<Product> update(
            @PathVariable String name,
            @RequestBody ProductCreateDTO dto) {

        Product updated = productService.updateProduct(name, dto);

        return ResponseEntity.ok(updated);
    }

   @DeleteMapping("/eliminarProduct")
   public ResponseEntity eliminar(@RequestParam String nombre){

     productService.deleteProduct(nombre);

    return ResponseEntity.noContent().build();


   }

   @GetMapping("/TodoslosProductos")
   public ResponseEntity<List<Product>> allProducts() {
       return ResponseEntity.ok(productService.todoslosProductos());
   }
   
    
    
}