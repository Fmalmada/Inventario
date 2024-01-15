package com.inventario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventario.dto.ObjetoDto;
import com.inventario.mappers.ObjetoMapper;
import com.inventario.modelo.Objeto;
import com.inventario.repository.ObjetoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjetoServiceImp implements ObjetoService {

    private final ObjetoRepository objetoRepository;
    private final ObjetoMapper mapper;
    
    @Override
    public List<ObjetoDto> getObjetos() {
        return mapper.map(objetoRepository.findAll());
    }

    public Long postObjeto(ObjetoDto objetoDto) {
        return objetoRepository.save(mapper.map(objetoDto)).getId();
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

    public ObjetoDto patchObjeto(Long id, ObjetoDto objetoDto) {
        Objeto objeto = objetoRepository.findById(id).orElseThrow(RuntimeException::new);
        
        if (objetoDto.getNombre() != null) {
            objeto.setNombre(objetoDto.getNombre());
        }

        if (objetoDto.getCantidad() != null) {
            objeto.setCantidad(objetoDto.getCantidad());
        }
        
        objetoRepository.save(objeto);

        return objetoDto;
    }

    public List<ObjetoDto> getObjetosContiene(String nombre) {
        return mapper.map(objetoRepository.findByNombreContaining(nombre));
    }

}
