package com.assessment.devsu.service;

import com.assessment.devsu.dto.ClienteDTO;
import com.assessment.devsu.dto.CuentaDTO;
import com.assessment.devsu.dto.MovimientoDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO create(ClienteDTO objDTO);
    ClienteDTO update(ClienteDTO objDTO);
    ClienteDTO findById(int id);
    void delete(int id);
    List<ClienteDTO> getAll();

}
