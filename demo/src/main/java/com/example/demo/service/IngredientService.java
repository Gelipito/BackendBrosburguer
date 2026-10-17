package com.example.demo.service;

import com.example.demo.dto.IngredientRequestDTO;
import com.example.demo.modelo.Ingredient;
import com.example.demo.repository.IngredientDao;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service
@RequiredArgsConstructor

public class IngredientService {

    private final IngredientDao ingredientDao;

    // CREATE
    public Ingredient registerIngredient(IngredientRequestDTO dto){

        if(ingredientDao.existsByName(dto.name().toLowerCase())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "El ingrediente ya existe");
        }

        Ingredient ingredient = new Ingredient();

        ingredient.setName(dto.name().toLowerCase());
        ingredient.setStock(dto.stock());
        ingredient.setPurchasePrice(dto.purchasePrice());
        ingredient.setExpirationDate(dto.expirationDate());

        return ingredientDao.save(ingredient);
    }

    // READ
    public Ingredient findIngredient(String name){

        Ingredient ingredient =
                ingredientDao.findByName(name.toLowerCase());

        if(ingredient == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "El ingrediente " + name + " no existe");
        }

        return ingredient;
    }

    // UPDATE
    public Ingredient updateIngredient(
            String name,
            IngredientRequestDTO dto){

        Ingredient ingredient = findIngredient(name);

        ingredient.setName(dto.name().toLowerCase());
        ingredient.setStock(dto.stock());
        ingredient.setPurchasePrice(dto.purchasePrice());
        ingredient.setExpirationDate(dto.expirationDate());

        return ingredientDao.save(ingredient);
    }

    // DELETE
    @Transactional
public void deleteIngredient(String name) {
    try {
        Ingredient ingredient = findIngredient(name);
        ingredientDao.delete(ingredient);
        // Forzamos el guardado inmediato en la base de datos dentro del try para capturar el error aquí
        ingredientDao.flush(); 
    } catch (DataIntegrityViolationException e) {
        // Lanzamos un estado HTTP 409 (Conflicto) con tu mensaje personalizado
        throw new ResponseStatusException(
            HttpStatus.CONFLICT, 
            "No se puede eliminar el ingrediente porque está siendo utilizado por uno o más productos."
        );
    }
}

    public List<Ingredient> todoslosingredientes(){
        return ingredientDao.findAll();
    }

}