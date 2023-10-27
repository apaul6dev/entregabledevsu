package com.assessment.devsu.service;

import com.assessment.devsu.dto.ClienteDTO;
import com.assessment.devsu.dto.CuentaDTO;

import java.util.List;

public interface CuentaService {

    CuentaDTO create(CuentaDTO objDTO);
    CuentaDTO update(CuentaDTO objDTO);
    CuentaDTO findById(int id);
    void delete(int id);
    List<CuentaDTO> getCuentasByCliente(int idCLiente);
    List<CuentaDTO> getAll();
}
