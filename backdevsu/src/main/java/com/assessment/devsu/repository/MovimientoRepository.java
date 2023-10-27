package com.assessment.devsu.repository;

import com.assessment.devsu.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query(value="SELECT m.id_movimiento, m.fecha, m.saldo, m.tipo, m.valor, m.id_cuenta " +
            "FROM movimiento m, cuenta c " +
            "where m.id_cuenta = c.id_cuenta " +
            "      and c.id_cuenta = :idCuenta " +
            "ORDER BY fecha DESC " +
            "LIMIT 1", nativeQuery = true)
    Movimiento getLastMovimiento(@Param("idCuenta") Integer idCuenta);

    @Query(value="SELECT m.id_movimiento, m.fecha, m.saldo, m.tipo, m.valor, m.id_cuenta " +
            "FROM movimiento m, cuenta c " +
            "where m.id_cuenta = c.id_cuenta " +
            "      and c.id_cuenta = :idCuenta " +
            "ORDER BY fecha DESC ", nativeQuery = true)
    List<Movimiento> getAll(@Param("idCuenta") Integer idCuenta);

    @Query(value="select " +
                "to_char(m.fecha,'DD/MM/YYYY') as fecha, " +
                "p.nombre as cliente, " +
                "c.numero_cuenta numerocuenta, " +
                "    case " +
                "    when m.tipo = 'retiro' then m.saldo + m.valor " +
                "    else m.saldo - m.valor " +
                " end as saldoinicial, " +
                " c.tipo_cuenta as tipocuenta, " +
                " c.estado, " +
                " m.valor as movimiento, " +
                " m.saldo as saldodiponible " +
            "from " +
                " movimiento m, cuenta c, cliente te, persona p " +
            "where " +
                " m.id_cuenta = c.id_cuenta " +
                " and c.id_cliente = te.id_cliente " +
                " and te.id_cliente = p.id_persona " +
                " and to_char(m.fecha,'DD/MM/YYYY') between :finicial and :ffinal " +
                " and c.numero_cuenta = :idCuenta "+
            "order by p.nombre, c.numero_cuenta, m.tipo, m.fecha ", nativeQuery = true)
    List<Object[]> reporte(@Param("finicial") String finicial, @Param("ffinal") String ffinal, @Param("idCuenta") int idCuenta);


}
