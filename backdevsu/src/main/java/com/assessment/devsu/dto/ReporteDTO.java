package com.assessment.devsu.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReporteDTO {
    String fecha;
    String cliente;
    String numeroCuenta;
    double saldoInicial;
    String tipo;
    String estado;
    double movimiento;
    double saldoDiponible;
}
