package com.inventario.permisos;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('objeto.editar')")
public @interface PermisoEditarObjeto {
    
}
