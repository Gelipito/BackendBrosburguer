package com.example.demo.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sales")
@Data
@NoArgsConstructor

public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    
    private Float total;
    

    @Column(nullable = false)
    private String ccCliente;

   @JsonManagedReference // <-- Añade esto para romper el ciclo JSON
    @ToString.Exclude     // <-- Evita bucles en el System.out.println de Lombok
    @EqualsAndHashCode.Exclude 
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleItem> items = new ArrayList<>();
}

    /* 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
*/  