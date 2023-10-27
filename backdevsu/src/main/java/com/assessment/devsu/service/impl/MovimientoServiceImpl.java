package com.assessment.devsu.service.impl;

import com.assessment.devsu.dto.MovimientoDTO;
import com.assessment.devsu.dto.ReporteDTO;
import com.assessment.devsu.exceptions.ResourceNotFoundException;
import com.assessment.devsu.exceptions.UnprocessableMovimiento;
import com.assessment.devsu.model.Movimiento;
import com.assessment.devsu.repository.MovimientoRepository;
import com.assessment.devsu.service.MovimientoService;
import com.assessment.devsu.util.MovimientoStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovimientoServiceImpl implements MovimientoService {

    private MovimientoRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public MovimientoServiceImpl(MovimientoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MovimientoDTO create(MovimientoDTO objDTO) {

        Movimiento movimiento = modelMapper.map(objDTO, Movimiento.class);
        Movimiento lastMovimiento = getLastMovimiento(movimiento.getCuenta().getIdCuenta());
        Double saldoTmp = null;

        if (lastMovimiento != null) {
            saldoTmp = lastMovimiento.getSaldo();
        } else {
            saldoTmp = movimiento.getCuenta().getSaldoInicial();
        }

        if (movimiento.getTipo().equals("deposito")) {
            movimiento.setSaldo(saldoTmp + movimiento.getValor());
        } else if (movimiento.getTipo().equals("retiro")) {
            Double debito = saldoTmp - movimiento.getValor();
            if (debito < 0) {
                throw new UnprocessableMovimiento(MovimientoStatus.SALDO_NO_DISPONIBLE.getStatus());
            }
            movimiento.setSaldo(debito);
        }

        movimiento = repository.save(movimiento);
        return modelMapper.map(movimiento, MovimientoDTO.class);
    }

    @Override
    public MovimientoDTO update(MovimientoDTO objDTO) {
        Movimiento movimiento = findObjectById(objDTO.getIdMovimiento());
        movimiento = modelMapper.map(objDTO, Movimiento.class);
        repository.save(movimiento);
        return modelMapper.map(movimiento, MovimientoDTO.class);
    }

    private Movimiento getLastMovimiento(int idCuenta) {
        return repository.getLastMovimiento(idCuenta);
    }

    private List<Movimiento> getAllMovimientos(int idCuenta) {
        return repository.getAll(idCuenta);
    }

    @Override
    public MovimientoDTO findById(int id) {
        Movimiento movimiento = findObjectById(id);
        return modelMapper.map(movimiento, MovimientoDTO.class);
    }

    @Override
    public List<MovimientoDTO> getAllByCuenta(int idCuenta) {
        List<Movimiento> monsters = repository.getAll(idCuenta);
        return monsters.stream().map(movimiento -> modelMapper.map(movimiento, MovimientoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        Movimiento movimiento = findObjectById(id);
        repository.delete(movimiento);
    }

    @Override
    public List<ReporteDTO> reporteJSON(String finicial, String ffinal, int idCuenta) {
        List<ReporteDTO> movimientos = new ArrayList<>();
        repository.reporte(finicial, ffinal, idCuenta).forEach(x -> {
            ReporteDTO m = new ReporteDTO();
            m.setFecha(String.valueOf(x[0]));
            m.setCliente(String.valueOf(x[1]));
            m.setNumeroCuenta(String.valueOf(x[2]));
            m.setSaldoInicial(Double.parseDouble(String.valueOf(x[3])));
            m.setTipo(String.valueOf(x[4]));
            m.setEstado(String.valueOf(x[5]));
            m.setMovimiento(Double.parseDouble(String.valueOf(x[6])));
            m.setSaldoDiponible(Double.parseDouble(String.valueOf(x[7])));
            movimientos.add(m);
        });
        return movimientos;
    }

    @Override
    public String reportePDF(String finicial, String ffinal, int idCuenta) throws JsonProcessingException {
        List<ReporteDTO> reporteList = reporteJSON(finicial, ffinal, idCuenta);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(reporteList);

        // Encode the JSON string as Base64
       // String base64EncodedData = Base64.getEncoder().encodeToString(json.getBytes());
        return Base64.getEncoder().encodeToString(json.getBytes());
       // return Base64.getDecoder().decode(base64EncodedData);

    }

    private Movimiento findObjectById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento not found"));
    }
}
