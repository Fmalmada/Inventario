package com.inventario.service;

import com.inventario.dto.ObjetoDto;
import com.inventario.repository.ObjetoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjetoServiceImp implements ObjetoService {

    private final ObjetoRepository objetoRepository;
    
    @Override
    public ObjetoDto getObjetos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getObjetos'");
    }
    
}
