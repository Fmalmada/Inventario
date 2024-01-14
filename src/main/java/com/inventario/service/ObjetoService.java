package com.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventario.dto.ObjetoDto;

@Service
public interface ObjetoService {
    
    public List<ObjetoDto> getObjetos();

    public ObjetoDto postObjeto(ObjetoDto objetoDto);

    public void deleteObjeto(Long id);

    public ObjetoDto putObjeto(Long id, ObjetoDto objetoDto);

    public ObjetoDto patchObjeto(Long id, ObjetoDto objetoDto);

    public List<ObjetoDto> getObjetosContiene(String nombre);
}
