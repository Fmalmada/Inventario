package com.inventario.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ObjetoDto {
    private String nombre;
    private Integer cantidad;
}
