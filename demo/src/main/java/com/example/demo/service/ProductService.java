package com.example.demo.service;

import com.example.demo.dto.productRequestDTO;
import com.example.demo.dto.ProductCreateDTO;
import com.example.demo.dto.productIngredientRequestDTO;
import com.example.demo.modelo.Ingredient;
import com.example.demo.modelo.Product;
import com.example.demo.modelo.ProductIngredient;
import com.example.demo.repository.ProductDao;
import com.example.demo.repository.ProductIngredientDao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private final ProductDao productDao;
    private final ProductIngredientDao productIngredientDao;
    private final ProductIngredientService productIngredientService;
  
    @Transactional
    public Product registerProduct(productRequestDTO productWithIngredients) {


    System.out.println("=== registerProduct called ===");

        ProductCreateDTO productoDto=productWithIngredients.product();
        
        // 1. Verificas si existe
        if (productDao.existsByName(productoDto.name().toLowerCase().trim())) {
            // Lanzamos un 409 Conflict en lugar de un error 500
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El producto ya existe");
        }

        // 2. Mapeas el DTO a la Entidad
        Product producto = new Product();
        producto.setName(productoDto.name().toLowerCase());
        producto.setSellingPrice(productoDto.sellingPrice());
        
        // Las listas ya se inicializan solas por el new ArrayList<>() en tu entidad
       Product productosave=productDao.save(producto);
        //llenamos los ingredientes que necesita el producto
        productIngredientService.registrarProductIngredient(productosave, productWithIngredients.ingredients());
        // 4. Guardas en la base de datos
        System.out.println("Saving product...");
        return  productosave;
    }

    public Product  findProduct (String name){
        
        Product  productofind=productDao.findByName(name.toLowerCase());

        if (productofind == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El producto " + name + " no existe");
        }
        
        return productofind;

    }

    public Product updateProduct(String name, ProductCreateDTO dto) {

    Product product = findProduct(name.toLowerCase());

    product.setName(dto.name().toLowerCase());
    product.setSellingPrice(dto.sellingPrice());

    return productDao.save(product);
}

public void deleteProduct(String name) {

    Product product = findProduct(name.toLowerCase());

    productDao.delete(product);
}

public List<Product> todoslosProductos(){

    return productDao.findAll();
}



}


 
