package com.inventario.service;

import java.util.List;

import com.inventario.dto.ObjetoDto;
import com.inventario.mappers.ObjetoMapper;
import com.inventario.repository.ObjetoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjetoServiceImp implements ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final ObjetoMapper mapper;
    
    @Override
    public List<ObjetoDto> getObjetos() {
        return mapper.map(objetoRepository.findAll());
    }
    
}
