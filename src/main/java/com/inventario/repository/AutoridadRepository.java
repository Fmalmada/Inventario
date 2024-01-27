package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.modelo.Autoridad;

public interface AutoridadRepository extends JpaRepository<Autoridad,Long> {
    
}
