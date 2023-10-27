package com.assessment.devsu.dto;

import com.univocity.parsers.annotations.Parsed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CuentaDTO {

    private int idCuenta;

    private Integer numeroCuenta;

    private String tipoCuenta;

    private Double saldoInicial;

    private String estado;

    private ClienteDTO cliente;
}
