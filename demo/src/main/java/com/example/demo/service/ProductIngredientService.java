package com.example.demo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.modelo.Product;
import com.example.demo.modelo.ProductIngredient;
import com.example.demo.repository.IngredientDao;
import com.example.demo.repository.ProductIngredientDao;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import com.example.demo.dto.productIngredientRequestDTO;
import com.example.demo.modelo.Ingredient;

@Service
@AllArgsConstructor
public class ProductIngredientService {
    private ProductIngredientDao productIngredientDao;
    private final IngredientDao ingredientRepository;
     private final IngredientService ingredientService;


   @Transactional // Garantiza que si un ingrediente falla, ningún stock se guarde (Rollback)
    public void restarCantidad(Product product, Integer cantidadVendida) {

        List<ProductIngredient> receta = productIngredientDao.findByProduct(product);

        // Usamos forEach para validar y restar elemento por elemento
        receta.forEach(pi -> {
            Ingredient ingredient = pi.getIngredient();
            Float stockActualIngredient = ingredient.getStock();

            Float cantidadNecesariaPorUnidad = pi.getRequiredQuantity();
            Float totalADescontar = cantidadNecesariaPorUnidad * cantidadVendida;

            // 1. VALIDACIÓN: Usamos THROW en lugar de RETURN
            if (totalADescontar > stockActualIngredient) {
                throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, // Es mejor usar BAD_REQUEST o PRECONDITION_FAILED
                    "No hay suficiente " + ingredient.getName() + " para hacer la receta: " + product.getName()
                );
            }

            // 2. ACTUALIZACIÓN: Restamos el stock del ingrediente usado
            ingredient.setStock(stockActualIngredient - totalADescontar);
            
            // 3. PERSISTENCIA: Guardamos el nuevo stock en la base de datos
            ingredientRepository.save(ingredient);
        });
    }

public void  registrarProductIngredient(Product product,List<productIngredientRequestDTO> ingredientsDto){

    for (productIngredientRequestDTO i : ingredientsDto) {

        Ingredient ingredienteObjeto= ingredientService.findIngredient(i.name().toLowerCase().trim());

        ProductIngredient productIngredient= new ProductIngredient();

        productIngredient.setProduct(product);
        productIngredient.setIngredient(ingredienteObjeto);
        productIngredient.setRequiredQuantity(i.requiredQuantity());

        productIngredientDao.save(productIngredient);
        

    }

}}
