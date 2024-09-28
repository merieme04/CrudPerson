package com.test.crudperson.entities;


import com.test.crudperson.enums.Ville;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    private String cin;

    @NotNull
    private String email;

    @NotNull @Enumerated(EnumType.STRING)
    private Ville ville;

    @NotNull
    private double telephone;

    @NotNull
    private String mot_de_passe;
}
