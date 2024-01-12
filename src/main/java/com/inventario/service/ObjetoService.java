package com.inventario.service;

import org.springframework.stereotype.Service;

import com.inventario.dto.ObjetoDto;

@Service
public interface ObjetoService {
    
    public ObjetoDto getObjetos();
}
