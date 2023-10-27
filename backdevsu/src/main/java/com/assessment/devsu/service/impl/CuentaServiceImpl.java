package com.assessment.devsu.service.impl;

import com.assessment.devsu.dto.ClienteDTO;
import com.assessment.devsu.dto.CuentaDTO;
import com.assessment.devsu.exceptions.ResourceNotFoundException;
import com.assessment.devsu.exceptions.UnprocessableEntityException;
import com.assessment.devsu.model.Cliente;
import com.assessment.devsu.model.Cuenta;
import com.assessment.devsu.repository.CuentaRepository;
import com.assessment.devsu.service.CuentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaServiceImpl implements CuentaService {

    private CuentaRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    public CuentaServiceImpl(CuentaRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CuentaDTO create(CuentaDTO objDTO) {
        Cuenta cuenta = modelMapper.map(objDTO, Cuenta.class);
        cuenta = repository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO update(CuentaDTO objDTO) {
        Cuenta cuenta = findObjectById(objDTO.getIdCuenta());
        cuenta = modelMapper.map(objDTO, Cuenta.class);
        repository.save(cuenta);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public CuentaDTO findById(int id) {
        Cuenta cuenta = findObjectById(id);
        return modelMapper.map(cuenta, CuentaDTO.class);
    }

    @Override
    public void delete(int id) {
        try{
            Cuenta cuenta = findObjectById(id);
            repository.delete(cuenta);
        }catch (Exception e){
            throw new UnprocessableEntityException("La cuenta tiene varios movimientos, no se puede eliminar.!");
        }

    }

    @Override
    public List<CuentaDTO> getCuentasByCliente(int idCLiente) {
        List<Cuenta> cuentas = repository.getCuentasByCliente(idCLiente);
        return cuentas.stream().map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CuentaDTO> getAll() {
        List<Cuenta> cuentas = repository.findAll();
        return cuentas.stream().map(cuenta -> modelMapper.map(cuenta, CuentaDTO.class))
                .collect(Collectors.toList());
    }

    private Cuenta findObjectById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta not found"));
    }
}
