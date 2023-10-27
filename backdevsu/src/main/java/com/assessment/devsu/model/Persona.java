package com.assessment.devsu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "genero" , nullable = false)
    private String genero;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "identificacion", nullable = true,  unique = true)
    private String identificacion;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = true)
    private String telefono;

}
