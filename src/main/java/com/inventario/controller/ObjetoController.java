package com.inventario.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.inventario.dto.ObjetoDto;
import com.inventario.service.ObjetoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inventario")
public class ObjetoController {
    
    private final ObjetoService objetoService;

    @GetMapping()
    public ResponseEntity<List<ObjetoDto>> getObjetos(@RequestParam(required=false) String nombre) {
        return ResponseEntity.ok().body(objetoService.getObjetos(nombre));
    }

    @PostMapping()
    public ResponseEntity<ObjetoDto> postObjeto(@RequestBody ObjetoDto objetoDto, UriComponentsBuilder uriBuilder) {
        UriComponents uriComponents =  uriBuilder.path("/inventario/{id}")
                                                .buildAndExpand(objetoService.postObjeto(objetoDto));

        return ResponseEntity.created(uriComponents.toUri()).body(objetoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteObjeto(@PathVariable Long id) {
        objetoService.deleteObjeto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetoDto> putObjeto(@PathVariable Long id, @RequestBody ObjetoDto objetoDto) {
        return ResponseEntity.ok().body(objetoService.putObjeto(id, objetoDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ObjetoDto> patchObjeto(@PathVariable Long id, @RequestBody ObjetoDto objetoDto) {
        return ResponseEntity.ok().body(objetoService.patchObjeto(id, objetoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetoDto> getObjetoPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(objetoService.getObjetoPorId(id));
    }

    
}
