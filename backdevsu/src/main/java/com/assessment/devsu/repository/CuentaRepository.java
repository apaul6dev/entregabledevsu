package com.assessment.devsu.repository;

import com.assessment.devsu.model.Cliente;
import com.assessment.devsu.model.Cuenta;
import com.assessment.devsu.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

    @Query(value="select c.id_cuenta, c.estado, c.numero_cuenta, c.saldo_inicial, tipo_cuenta , id_cliente  from cuenta c where c.id_cliente = :idCliente", nativeQuery = true)
    List<Cuenta> getCuentasByCliente(@Param("idCliente") Integer idCliente);
}
