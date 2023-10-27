package com.assessment.devsu.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "movimiento")
@Getter
@Setter
@NoArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimiento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = true)
    private Date fecha;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @Column(name = "saldo", nullable = false)
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;

    @PrePersist
    private void onCreate() {
        fecha = new Date();
    }

}
