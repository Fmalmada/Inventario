package com.inventario.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.modelo.Objeto;

public interface ObjetoRepository extends JpaRepository<Objeto, UUID> {
    
}
