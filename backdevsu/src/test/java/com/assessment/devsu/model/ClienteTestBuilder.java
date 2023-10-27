package com.assessment.devsu.model;

import com.assessment.devsu.dto.ClienteDTO;
import com.univocity.parsers.annotations.Parsed;
import lombok.Builder;

public class ClienteTestBuilder {
    @Builder
    public static ClienteDTO clienteDTO(int idPersona, String nombre, String genero, Integer edad, String identificacion,
                                     String direccion, String telefono, String contrasena, String estado) {
        ClienteDTO cliente = new ClienteDTO();

        cliente.setIdPersona(idPersona);
        cliente.setNombre(nombre);
        cliente.setGenero(genero);
        cliente.setEdad(edad);
        cliente.setIdentificacion(identificacion);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setContrasena(contrasena);
        cliente.setEstado(estado);

        return cliente;
    }
}
