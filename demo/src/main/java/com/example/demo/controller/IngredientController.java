package com.example.demo.controller;

import com.example.demo.dto.IngredientRequestDTO;
import com.example.demo.modelo.Ingredient;
import com.example.demo.service.IngredientService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    // CREATE
    @PostMapping("/registrar")
    public ResponseEntity<Ingredient> register(
            @RequestBody IngredientRequestDTO dto){

        Ingredient ingredient =
                ingredientService.registerIngredient(dto);

        return ResponseEntity.ok(ingredient);
    }

    // READ
    @GetMapping("/buscar")
    public ResponseEntity<Ingredient> search(
            @RequestParam String name){

        Ingredient ingredient =
                ingredientService.findIngredient(name);

        return ResponseEntity.ok(ingredient);
    }

    // UPDATE
    @PutMapping("/actualizar")
    public ResponseEntity<Ingredient> update(
            @RequestParam String name,
            @RequestBody IngredientRequestDTO dto){

        Ingredient ingredient =
                ingredientService.updateIngredient(name, dto);

        return ResponseEntity.ok(ingredient);
    }

    // DELETE
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> delete(
            @RequestParam String name){

        ingredientService.deleteIngredient(name);

        return ResponseEntity.ok(
                "Ingrediente eliminado correctamente");
    }


    @GetMapping("/todoslosIngredientes")
    public ResponseEntity<List<Ingredient>> allIngredients() {
        return ResponseEntity.ok(ingredientService.todoslosingredientes());
    }
    

}