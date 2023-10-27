package com.assessment.devsu.controller;

import com.assessment.devsu.dto.ClienteDTO;
import com.assessment.devsu.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteDTO getClienteById(@PathVariable("id") int idCliente) {
        return clienteService.findById(idCliente);
    }

    @PostMapping
    public ClienteDTO create(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.create(clienteDTO);
    }

    @PutMapping
    public ClienteDTO update(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.update(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int idCliente) {
        clienteService.delete(idCliente);
    }

    @GetMapping
    public List<ClienteDTO> getAll() {
        return clienteService.getAll();
    }
}
