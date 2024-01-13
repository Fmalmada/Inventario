package com.inventario.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.inventario.dto.ObjetoDto;

import com.inventario.modelo.Objeto;

@Mapper(componentModel = "spring")
public interface ObjetoMapper {
    
    public ObjetoDto map(Objeto objeto);

    @Mapping(target="id", ignore=true)
    public Objeto map(ObjetoDto objetoDTO);

    public List<ObjetoDto> map(List<Objeto> listaObjetos);

}
