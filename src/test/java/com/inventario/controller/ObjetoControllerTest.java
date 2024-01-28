package com.inventario.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventario.dto.ObjetoDto;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ObjetoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getObjetosAdmin() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin", "admin"))
                        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(3)))
                
                .andExpect(jsonPath("$[0].nombre", is("objeto A")))
                .andExpect(jsonPath("$[0].cantidad", is(10)))

                .andExpect(jsonPath("$[1].nombre", is("objeto B")))
                .andExpect(jsonPath("$[1].cantidad", is(15)))
                
                .andExpect(jsonPath("$[2].nombre", is("ejemplo para empezar")))
                .andExpect(jsonPath("$[2].cantidad", is(1)));  
    }

    @Test
    void getObjetosCliente() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("cliente", "cliente"))
                        )

                    .andExpect(status().isOk())

                    .andExpect(jsonPath("$", hasSize(3)))
                        
                    .andExpect(jsonPath("$[0].nombre", is("objeto A")))
                    .andExpect(jsonPath("$[0].cantidad", is(10)))
        
                    .andExpect(jsonPath("$[1].nombre", is("objeto B")))
                    .andExpect(jsonPath("$[1].cantidad", is(15)))
                        
                    .andExpect(jsonPath("$[2].nombre", is("ejemplo para empezar")))
                    .andExpect(jsonPath("$[2].cantidad", is(1))); 
    }

    @Test
    void getObjetosSinUsuario() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void postObjetoAdmin() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/inventario")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("admin", "admin"))
                        )
                        .andExpect(status().isCreated())
                        .andExpect(MockMvcResultMatchers
                                    .content()
                                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void postObjetoCliente() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/inventario")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("cliente", "cliente"))
                        )
                        .andExpect(status().isForbidden());
    }

    @Test
    void postObjetoSinUsuario() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/inventario")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteObjetoAdmin() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/inventario/1") 
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("admin", "admin"))
                        )
                        .andExpect(status().isNoContent());
    }

    @Test
    void deleteObjetoCliente() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/inventario/1") 
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("cliente", "cliente"))
                        )
                        .andExpect(status().isForbidden());
    }

    @Test
    void deleteObjetoSinUsuario() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/inventario/1") 
                                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isUnauthorized());
    }

    @Test
    void putObjetoAdmin() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("admin", "admin"))
                        )
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers
                                    .content()
                                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void putObjetoCliente() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("cliente", "cliente"))
                        )
                        .andExpect(status().isForbidden());
    }

    @Test
    void putObjetoSinUsuario() throws Exception {
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .nombre("objeto de test")
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isUnauthorized());
    }

    @Test
    void patchObjetoAdmin() throws Exception{
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("admin", "admin"))
                        )
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers
                                    .content()
                                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void patchObjetoCliente() throws Exception{
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .with(httpBasic("cliente", "cliente"))
                        )
                        .andExpect(status().isForbidden());

    }

    @Test
    void patchObjetoSinUsuario() throws Exception{
        ObjetoDto objetoPost = ObjetoDto.builder()
                                        .cantidad(10)
                                        .build();
        mockMvc.perform(
                        MockMvcRequestBuilders.patch("/inventario/1")
                                                .content(mapper.writeValueAsString(objetoPost))
                                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isUnauthorized());
    }

    @Test
    void getObjetoPorNombreAdmin() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .queryParam("nombre", "objeto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin", "admin"))
                        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))
                
                .andExpect(jsonPath("$[0].nombre", is("objeto A")))
                .andExpect(jsonPath("$[0].cantidad", is(10)))

                .andExpect(jsonPath("$[1].nombre", is("objeto B")))
                .andExpect(jsonPath("$[1].cantidad", is(15))); 
    }

    @Test
    void getObjetoPorNombreCliente() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .queryParam("nombre", "objeto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("cliente", "cliente"))
                        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))
                
                .andExpect(jsonPath("$[0].nombre", is("objeto A")))
                .andExpect(jsonPath("$[0].cantidad", is(10)))

                .andExpect(jsonPath("$[1].nombre", is("objeto B")))
                .andExpect(jsonPath("$[1].cantidad", is(15)));

    }

    @Test
    void getObjetoPorNombreSinUsuario() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .queryParam("nombre", "objeto")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getObjetoPorIdAdmin() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin", "admin"))
                        )
            .andExpect(status().isOk())
    
            .andExpect(jsonPath("$.nombre", is("objeto A")))
            .andExpect(jsonPath("$.cantidad", is(10)));
    }

    @Test
    void getObjetoPorIdCliente() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("cliente", "cliente"))
                        )
            .andExpect(status().isOk())
    
            .andExpect(jsonPath("$.nombre", is("objeto A")))
            .andExpect(jsonPath("$.cantidad", is(10)));
    }

    @Test
    void getObjetoPorIdSinUsuario() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
            .andExpect(status().isUnauthorized());
    }
}
