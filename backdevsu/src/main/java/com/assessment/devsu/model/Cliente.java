package com.assessment.devsu.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id_cliente")
public class Cliente extends Persona {

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private String estado;

}
