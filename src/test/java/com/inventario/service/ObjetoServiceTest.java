package com.inventario.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.inventario.dto.ObjetoDto;
import com.inventario.mappers.ObjetoMapper;
import com.inventario.modelo.Objeto;
import com.inventario.repository.ObjetoRepository;

@ExtendWith(MockitoExtension.class)
public class ObjetoServiceTest {
    
    @Mock
    @Autowired
    private ObjetoRepository objetoRepo;

    @Mock
    @Autowired
    private ObjetoMapper objetoMapper;

    @InjectMocks
    private ObjetoServiceImp objetoService;

    private Objeto objetoA;
    private ObjetoDto objetoADto;

    private Objeto objetoB;
    private ObjetoDto objetoBDto;

    private List<Objeto> objetos;
    private List<ObjetoDto> objetosDto;

    @BeforeEach
    void setUp() {

        objetoA = Objeto.builder()
                            .nombre("objeto A")
                            .cantidad(3)
                            .build();

        objetoADto = ObjetoDto.builder()
                                .nombre(objetoA.getNombre())
                                .cantidad(objetoA.getCantidad())
                                .build();
        
        objetoB =  Objeto.builder()
                            .nombre("objeto B")
                            .cantidad(8)
                            .build();

        objetoBDto = ObjetoDto.builder()
                                .nombre(objetoB.getNombre())
                                .cantidad(objetoB.getCantidad())
                                .build();
        
        objetos = Arrays.asList(objetoA, objetoB);

        objetosDto = Arrays.asList(objetoADto, objetoBDto);
    }

    @Test
    void testGetObjetos() {
        when(objetoMapper.map(objetos)).thenReturn(objetosDto);
        when(objetoRepo.findAll()).thenReturn(objetos);

        assertEquals(objetoService.getObjetos(), objetosDto);

        verify(objetoMapper, times(1)).map(objetos);
        verify(objetoRepo,times(1)).findAll();
    }

    @Test
    void testPostObjeto() {
        when(objetoMapper.map(objetoADto)).thenReturn(objetoA);
        when(objetoRepo.save(objetoA)).thenReturn(objetoA);

        assertEquals(objetoService.postObjeto(objetoADto), objetoADto);

        verify(objetoMapper, times(1)).map(objetoADto);
        verify(objetoRepo, times(1)).save(objetoA);
    }
 }
