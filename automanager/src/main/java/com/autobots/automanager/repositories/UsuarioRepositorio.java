package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.automanager.entities.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
}
