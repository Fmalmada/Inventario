package com.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventario.modelo.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
}
