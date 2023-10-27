package com.assessment.devsu.controller;

import com.assessment.devsu.ApplicationConfig;
import com.assessment.devsu.dto.ClienteDTO;
import com.assessment.devsu.model.ClienteTestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String CLIENTES_PATH = "/clientes";

    @Test
    void deberiaGuardarUnCliente() throws Exception {
        ClienteDTO clienteDTO = ClienteTestBuilder.builder()
                .nombre("Carlos Test")
                .genero("Masculino")
                .edad(14)
                .identificacion("1234312333")
                .direccion("Cuenca/Ecuador/America")
                .telefono("1234454636")
                .contrasena("admin").estado("True").build();

        this.mockMvc.perform(post(CLIENTES_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk());

    }

    @Test
    void deberiaEliminarUnCliente() throws Exception {
        ClienteDTO clienteDTO = ClienteTestBuilder.builder()
                .nombre("Andrea Test")
                .genero("Femenino")
                .edad(14)
                .identificacion("1234312333")
                .direccion("Bogota/Colombia/America")
                .telefono("98999343434")
                .contrasena("admin").estado("True").build();

        ClienteDTO rs = objectMapper.readValue(
                mockMvc.perform(post(CLIENTES_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(clienteDTO))).andReturn()
                        .getResponse()
                        .getContentAsString(),
                ClienteDTO.class);

        this.mockMvc.perform(delete(CLIENTES_PATH + "/{id}", rs.getIdPersona()))
                .andExpect(status().isOk());

    }
}
