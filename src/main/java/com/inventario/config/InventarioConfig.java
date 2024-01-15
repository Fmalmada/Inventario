package com.inventario.config;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inventario.modelo.Objeto;
import com.inventario.repository.ObjetoRepository;

@Configuration
public class InventarioConfig {

    @Bean
    CommandLineRunner inicializarBaseDeDatos(ObjetoRepository objetoRepo) {
        return args -> {
            objetoRepo.saveAll(Arrays.asList(
                                            Objeto.builder()
                                                    .nombre("objeto A")
                                                    .cantidad(10)
                                                    .build(),

                                            Objeto.builder()
                                                    .nombre("objeto B")
                                                    .cantidad(15)
                                                    .build()
                                                    
                                         )
             );
        };
    }
    
}
