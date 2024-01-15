package com.inventario.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ObjetoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getObjetos() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$", hasSize(2)))
                
                .andExpect(jsonPath("$[0].nombre", is("objeto A")))
                .andExpect(jsonPath("$[0].cantidad", is(10)))

                .andExpect(jsonPath("$[1].nombre", is("objeto B")))
                .andExpect(jsonPath("$[1].cantidad", is(15)));    
    }
}
