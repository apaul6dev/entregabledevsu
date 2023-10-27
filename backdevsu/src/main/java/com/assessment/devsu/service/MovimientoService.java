package com.assessment.devsu.service;

import com.assessment.devsu.dto.MovimientoDTO;
import com.assessment.devsu.dto.ReporteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface MovimientoService {

    MovimientoDTO create(MovimientoDTO objDTO);
    MovimientoDTO update(MovimientoDTO objDTO);
    MovimientoDTO findById(int id);
    List<MovimientoDTO> getAllByCuenta(int idCuenta);
    void delete(int id);

    List<ReporteDTO>reporteJSON(String finicial, String ffinal, int idCuenta);
    String reportePDF(String finicial, String ffinal, int idCuenta) throws JsonProcessingException;

}
