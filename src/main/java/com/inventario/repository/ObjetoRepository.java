package com.inventario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.modelo.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, Long> {

    public List<Objeto> findByNombreContaining(String nombre);
    
    
}
