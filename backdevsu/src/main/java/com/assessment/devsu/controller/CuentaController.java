package com.assessment.devsu.controller;

import com.assessment.devsu.dto.CuentaDTO;
import com.assessment.devsu.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public CuentaDTO getCuentaById(@PathVariable("id") int idCuenta) {
        return cuentaService.findById(idCuenta);
    }

    @PostMapping
    public CuentaDTO create(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.create(cuentaDTO);
    }

    @PutMapping
    public CuentaDTO update(@RequestBody CuentaDTO cuentaDTO) {
        return cuentaService.update(cuentaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int idCuenta) {
        cuentaService.delete(idCuenta);
    }

    @GetMapping
    public List<CuentaDTO> getAll() {
        return cuentaService.getAll();
    }
    @GetMapping("getCuentasByCliente/{id}")
    public List<CuentaDTO> getCuentasByCliente(@PathVariable("id") int idCliente) {
        return cuentaService.getCuentasByCliente(idCliente);
    }
}
