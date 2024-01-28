package com.inventario.config;

import java.util.Arrays;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.inventario.modelo.Autoridad;
import com.inventario.modelo.Objeto;
import com.inventario.modelo.Rol;
import com.inventario.modelo.Usuario;
import com.inventario.repository.AutoridadRepository;
import com.inventario.repository.ObjetoRepository;
import com.inventario.repository.RolRepository;
import com.inventario.repository.UsuarioRepository;

@Configuration
public class InventarioConfig {

    @Bean
    CommandLineRunner inicializarBaseDeDatos(ObjetoRepository objetoRepo, UsuarioRepository usuarioRepo, RolRepository rolRepo, AutoridadRepository autoRepo, PasswordEncoder encoder) {
        return args -> {
            guardarObjetos(objetoRepo);
            guardarUsuarios(usuarioRepo, rolRepo, autoRepo, encoder);
        };
    }

    private void guardarUsuarios(UsuarioRepository usuarioRepo, RolRepository rolRepo, AutoridadRepository autoRepo, PasswordEncoder encoder) {
        Autoridad crearObjeto = autoRepo.save(Autoridad.builder().permiso("objeto.crear").build());
        Autoridad leerObjeto = autoRepo.save(Autoridad.builder().permiso("objeto.leer").build());
        Autoridad editarObjeto = autoRepo.save(Autoridad.builder().permiso("objeto.editar").build());
        Autoridad eliminarObjeto = autoRepo.save(Autoridad.builder().permiso("objeto.eliminar").build());

        Rol admin = rolRepo.save(Rol.builder().nombre("ADMIN").build());
        Rol cliente = rolRepo.save(Rol.builder().nombre("CLIENTE").build());

        admin.setAutoridades(Set.of(crearObjeto, leerObjeto, editarObjeto, eliminarObjeto));
        cliente.setAutoridades(Set.of(leerObjeto));

        rolRepo.saveAll(Arrays.asList(admin, cliente));

        Usuario adminUsuario = usuarioRepo.save(Usuario.builder().username("admin").password(encoder.encode("admin")).build());
        Usuario clienteEjemplo = usuarioRepo.save(Usuario.builder().username("cliente").password(encoder.encode("cliente")).build());
        
        adminUsuario.setRoles(Set.of(admin));
        clienteEjemplo.setRoles(Set.of(cliente));

        usuarioRepo.saveAll(Arrays.asList(adminUsuario, clienteEjemplo));
    
    }

    private void guardarObjetos(ObjetoRepository objetoRepo) {

        objetoRepo.saveAll(Arrays.asList(
                                            Objeto.builder()
                                                    .nombre("objeto A")
                                                    .cantidad(10)
                                                    .build(),

                                            Objeto.builder()
                                                    .nombre("objeto B")
                                                    .cantidad(15)
                                                    .build(),
                                            
                                            Objeto.builder()
                                                    .nombre("ejemplo para empezar")
                                                    .cantidad(1)
                                                    .build()
                                                
                                                    
                                         )
                            );
    }
    
}
