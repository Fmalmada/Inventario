package com.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventario.dto.ObjetoDto;

@Service
public interface ObjetoService {
    
    public List<ObjetoDto> getObjetos();
}
