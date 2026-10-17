package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float sellingPrice; 

 @JsonManagedReference // <-- Añade esto para romper el ciclo JSON
    @ToString.Exclude     // <-- Evita bucles en el System.out.println de Lombok
    @EqualsAndHashCode.Exclude 
    @OneToMany(
    mappedBy = "product",
    cascade = CascadeType.ALL,
    orphanRemoval = true,
    fetch = FetchType.LAZY
)
private List<ProductIngredient> ingredients = new ArrayList<>();
}