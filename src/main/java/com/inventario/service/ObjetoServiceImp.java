package com.inventario.service;

import java.util.List;

import com.inventario.dto.ObjetoDto;
import com.inventario.mappers.ObjetoMapper;
import com.inventario.modelo.Objeto;
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

    public ObjetoDto postObjeto(ObjetoDto objetoDto) {
        objetoRepository.save(mapper.map(objetoDto));
        return objetoDto;
    }

    @Override
    public void deleteObjeto(Long id) {
        if (!objetoRepository.existsById(id)){
            throw(new RuntimeException());      
        }
        objetoRepository.deleteById(id);
    }

    public ObjetoDto putObjeto(Long id, ObjetoDto objetoDto) {
        if (!objetoRepository.existsById(id)) {
            throw(new RuntimeException()); 
        }

        Objeto objeto = mapper.map(objetoDto);
        objeto.setId(id);

        objetoRepository.save(objeto);
        return objetoDto;
    }

}
