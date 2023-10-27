package com.assessment.devsu.controller;

import com.assessment.devsu.dto.CuentaDTO;
import com.assessment.devsu.dto.MovimientoDTO;
import com.assessment.devsu.dto.ReporteDTO;
import com.assessment.devsu.service.CuentaService;
import com.assessment.devsu.service.MovimientoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/{id}")
    public MovimientoDTO getMovimientoById(@PathVariable("id") int idMovimiento) {
        return movimientoService.findById(idMovimiento);
    }

    @PostMapping
    public MovimientoDTO create(@RequestBody MovimientoDTO movimientoDTO) {
        CuentaDTO cuentaDTO = cuentaService.findById(movimientoDTO.getCuenta().getIdCuenta());
        movimientoDTO.setCuenta(cuentaDTO);
        return movimientoService.create(movimientoDTO);
    }

    @GetMapping("getAllByCuenta/{id}")
    public List<MovimientoDTO> getAllByCuenta(@PathVariable("id") int idCuenta) {
        cuentaService.findById(idCuenta);
        return movimientoService.getAllByCuenta(idCuenta);
    }

    @PutMapping
    public MovimientoDTO update(@RequestBody MovimientoDTO movimientoDTO) {
        return movimientoService.update(movimientoDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int idMovimiento) {
        movimientoService.delete(idMovimiento);
    }

    @GetMapping("/generateJSONReport")
    public List<ReporteDTO> generateJSONReport(@RequestParam("finicial") String finicial, @RequestParam("ffinal") String ffinal, @RequestParam("idCuenta") int idCuenta) {
        return movimientoService.reporteJSON(finicial, ffinal, idCuenta);
    }

    @GetMapping("/generatePDFReport")
    public String generatePDFReport(@RequestParam("finicial") String finicial, @RequestParam("ffinal") String ffinal, @RequestParam("idCuenta") int idCuenta) throws JsonProcessingException {
        String result = "{\"base\":\"data\"}";
        String data = result.replace("data", movimientoService.reportePDF(finicial, ffinal, idCuenta));
        return data;
    }
}
