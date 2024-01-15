package com.inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inventario.dto.ObjetoDto;
import com.inventario.service.ObjetoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventario")
public class ObjetoController {
    
    private final ObjetoService objetoService;

    @GetMapping()
    public ResponseEntity<List<ObjetoDto>> getObjetos() {
        return ResponseEntity.ok().body(objetoService.getObjetos());
    }

    
}
